import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { ChatService } from '../../services/chat.service';
import { ChatMessage } from '../../models/chat-message.model';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.scss'
})
export class ChatComponent implements OnInit {
  messages: ChatMessage[] = [];
  newMessage = '';
  currentUser!: User;

  constructor(private chatService: ChatService, private authService: AuthService) {}

  ngOnInit(): void {
    this.currentUser = this.authService.getUser();

    this.chatService.messages$.subscribe((msgs) => {
      this.messages = msgs;
    });
  }

  sendMessage(): void {
    if (!this.newMessage.trim()) return;

    const msg: ChatMessage = {
      user_id: this.currentUser.user_id,
      text: this.newMessage,
      created_at: new Date(),
      name: this.currentUser.name,
      firstname: this.currentUser.firstname,
    };

    this.chatService.sendMessage(msg);
    this.newMessage = '';
  }

  isMyMessage(msg: ChatMessage): boolean {
    return msg.user_id === this.currentUser.user_id;
  }

  formatTime(date: string | Date): string {
    const d = new Date(date);
    return d.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
  }
}

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
export class ChatComponent {
  messages: ChatMessage[] = [];
  newMessage = '';
  currentUser!: User;

  constructor(private chatService: ChatService, private authService: AuthService) {}

  ngOnInit() {
    this.currentUser = this.authService.getUser();
    this.loadMessages();
  }

  sendMessage() {
    if (!this.newMessage.trim()) return;
    this.chatService.sendMessage(this.newMessage).subscribe(msg => {
      this.messages.push(msg);
      this.newMessage = '';
    });
  }

  loadMessages() {
    this.chatService.getMessages().subscribe(msgs => {
      this.messages = msgs;
    });
  }

  isMyMessage(msg: ChatMessage): boolean {
    return msg.user_id === this.currentUser.user_id;
  }
}

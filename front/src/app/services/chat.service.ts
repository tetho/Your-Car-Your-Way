import { Injectable } from '@angular/core';
import { ChatMessage } from '../models/chat-message.model';
import { BehaviorSubject } from 'rxjs';
import { Client, IMessage } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private stompClient!: Client;
  private messagesSubject = new BehaviorSubject<ChatMessage[]>([]);
  public messages$ = this.messagesSubject.asObservable();
  private messages: ChatMessage[] = [];

  constructor() {
    this.connect();
  }

  private connect(): void {
    this.stompClient = new Client({
      webSocketFactory: () => new SockJS('http://localhost:9000/ws'),
      reconnectDelay: 5000,
      connectHeaders: {
        Authorization: `Bearer ${localStorage.getItem('token')}`
      },
      debug: (str) => console.log(str),
    });

    this.stompClient.onConnect = () => {
      console.log('WebSocket connected');

      this.stompClient.subscribe('/topic/messages', (message: IMessage) => {
        const raw: ChatMessage = JSON.parse(message.body);
        const msg: ChatMessage = {
          ...raw,
          created_at: new Date(raw.created_at)
        };
        this.messages.push(msg);
        this.messagesSubject.next([...this.messages]);
      });
    };

    this.stompClient.onStompError = frame => {
      console.error('Stomp error:', frame.headers['message'], frame.body);
    };

    this.stompClient.activate();
  }

  public sendMessage(message: ChatMessage): void {
    if (this.stompClient && this.stompClient.connected) {
      this.stompClient.publish({
        destination: '/app/chat',
        body: JSON.stringify(message),
      });
    }
  }
}

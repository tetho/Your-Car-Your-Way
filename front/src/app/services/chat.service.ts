import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ChatMessage } from '../models/chat-message.model';
import { AuthService } from './auth.service';
import { SupportRequest } from '../models/support-request.model';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private baseUrl = 'http://localhost:9000';
  
  private supportRequestId!: number;

  constructor(private http: HttpClient, private authService: AuthService) {}

  createSupportRequestForCustomer() {
    const user = this.authService.getUser();
    return this.http.post<any>(this.baseUrl + `support-requests`, { userId: user.user_id }, { withCredentials: true });
  }

  setSupportRequestId(id: number) {
    this.supportRequestId = id;
  }

  getSupportRequestId(): number {
    return this.supportRequestId;
  }

  sendMessage(text: string) {
    const user = this.authService.getUser();
    const message: ChatMessage = {
      support_request_id: this.supportRequestId,
      user_id: user.user_id,
      text: text
    };
    return this.http.post<ChatMessage>(`${this.baseUrl}/chat`, message, { withCredentials: true });
  }

  getMessages() {
    return this.http.get<ChatMessage[]>(this.baseUrl + `/chat/support-request/${this.supportRequestId}`, { withCredentials: true });
  }

  getLatestSupportRequest() {
    return this.http.get<SupportRequest>(`${this.baseUrl}/support-requests/latest`, { withCredentials: true });
  }
}

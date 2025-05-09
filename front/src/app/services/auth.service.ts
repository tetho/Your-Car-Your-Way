import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { User } from '../models/user.model';
import { AuthSuccess } from '../interfaces/auth-success';
import { LoginRequest } from '../interfaces/login-request';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:9000';
  private apiUrl = this.baseUrl + '/auth';

  private user!: User;

  constructor(private httpClient: HttpClient) { }

  public login(loginRequest: LoginRequest): Observable<AuthSuccess> {
    return this.httpClient.post<AuthSuccess>(`${this.apiUrl}/login`, loginRequest);
  }

  public me(): Observable<User> {
    return this.httpClient.get<User>(`${this.apiUrl}/me`);
  }

  public getUser(): User {
    return this.user;
  }

  public getUserInfo() {
    return this.httpClient.get<User>(`${this.baseUrl}/me`, { withCredentials: true }).pipe(
      tap(user => (this.user = user))
    );
  }

}

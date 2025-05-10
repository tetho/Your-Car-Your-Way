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
  private baseUrl = 'http://localhost:9000/auth';
  private user!: User;

  constructor(private http: HttpClient) {}

  public login(request: LoginRequest): Observable<AuthSuccess> {
    return this.http.post<AuthSuccess>(`${this.baseUrl}/login`, request);
  }

  public me(): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/me`, { withCredentials: true }).pipe(
      tap(user => this.user = user)
    );
  }

  public getUser(): User {
    return this.user;
  }
}

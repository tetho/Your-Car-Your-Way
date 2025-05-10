import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  private isLogged = false;
  private user?: User;

  private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);

  public $isLogged(): Observable<boolean> {
    return this.isLoggedSubject.asObservable();
  }

  public logIn(user: User): void {
    this.user = user;
    this.isLogged = true;
    this.isLoggedSubject.next(this.isLogged);
  }

  public logOut(): void {
    this.user = undefined;
    this.isLogged = false;
    this.isLoggedSubject.next(this.isLogged);
  }

  public getUser(): User | undefined {
    return this.user;
  }

  public isAuthenticated(): boolean {
    return this.isLogged;
  }
}

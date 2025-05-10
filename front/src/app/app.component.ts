import { Component, OnInit } from '@angular/core';
import { AuthService } from './services/auth.service';
import { SessionService } from './services/session.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  title = 'Your Car Your Way';
  isLoggedIn = false;

  constructor(
    private authService: AuthService,
    private sessionService: SessionService
  ) {}

  ngOnInit(): void {
    const token = localStorage.getItem('token');
    if (token) {
      this.authService.me().subscribe({
        next: user => {
          this.sessionService.logIn(user);
          this.isLoggedIn = true;
        },
        error: () => {
          this.isLoggedIn = false;
          localStorage.removeItem('token');
        }
      });
    }
  }
}

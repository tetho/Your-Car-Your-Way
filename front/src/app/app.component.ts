import { Component, OnInit } from '@angular/core';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  title = 'Your Car Your Way';

  isLoggedIn = false;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    const token = localStorage.getItem('jwt');
    if (token) {
      this.authService.getUserInfo().subscribe({
        next: () => {
          this.isLoggedIn = true;
        },
        error: () => {
          this.isLoggedIn = false;
          localStorage.removeItem('jwt');
        }
      });
    }
  }
}

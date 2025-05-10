import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthSuccess } from '../../interfaces/auth-success';
import { SessionService } from '../../services/session.service';
import { LoginRequest } from '../../interfaces/login-request';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  public onError = false;

  public loginForm = this.fb.group({
    email: ['', [Validators.required, Validators.min(3)]],
    password: ['', [Validators.required, Validators.min(3)]]
  });

  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private router: Router,
    private sessionService: SessionService
  ) {}

  public submit(): void {
    const loginRequest = this.loginForm.value as LoginRequest;

    this.authService.login(loginRequest).subscribe({
      next: (response: AuthSuccess) => {
        localStorage.setItem('token', response.token);

        this.authService.me().subscribe({
          next: (user: User) => {
            this.sessionService.logIn(user);

            this.router.navigate(['/chat']);
          },
          error: () => this.onError = true
        });
      },
      error: () => this.onError = true
    });
  }
}

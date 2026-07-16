import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private isLoggedIn = true; // Hardcoded for now - will be replaced with real auth later

  login(): void {
    this.isLoggedIn = true;
  }

  logout(): void {
    this.isLoggedIn = false;
  }

  checkAuth(): boolean {
    return this.isLoggedIn;
  }
}
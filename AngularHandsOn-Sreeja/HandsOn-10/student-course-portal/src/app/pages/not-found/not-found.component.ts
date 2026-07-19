import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-not-found',
  standalone: true,
  imports: [RouterLink],
  template: `
    <div class="not-found">
      <h1>404</h1>
      <h2>Page Not Found</h2>
      <p>The page you are looking for does not exist.</p>
      <a routerLink="/">Go to Home</a>
    </div>
  `,
  styles: [`
    .not-found {
      text-align: center;
      padding: 60px 20px;
      max-width: 500px;
      margin: 0 auto;
    }
    .not-found h1 {
      font-size: 6rem;
      color: #e74c3c;
      margin: 0;
    }
    .not-found h2 {
      color: #2c3e50;
      margin: 20px 0;
    }
    .not-found p {
      color: #7f8c8d;
      margin-bottom: 30px;
    }
    .not-found a {
      display: inline-block;
      padding: 12px 30px;
      background-color: #3498db;
      color: white;
      text-decoration: none;
      border-radius: 4px;
      transition: background-color 0.3s;
    }
    .not-found a:hover {
      background-color: #2980b9;
    }
  `]
})
export class NotFoundComponent {}
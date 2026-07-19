import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationService } from '../../services/notification.service';

@Component({
  selector: 'app-notification',
  standalone: true,
  imports: [CommonModule],
  providers: [NotificationService],
  template: `
    <div class="notification" *ngIf="message">
      <span>{{ message }}</span>
      <button (click)="clear()">×</button>
    </div>
  `,
  styles: [`
    .notification {
      position: fixed;
      bottom: 20px;
      right: 20px;
      padding: 15px 25px;
      background-color: #2c3e50;
      color: white;
      border-radius: 8px;
      box-shadow: 0 4px 12px rgba(0,0,0,0.3);
      display: flex;
      align-items: center;
      gap: 15px;
      z-index: 1000;
      animation: slideIn 0.3s ease;
    }
    .notification button {
      background: none;
      border: none;
      color: white;
      font-size: 1.5rem;
      cursor: pointer;
      padding: 0 5px;
    }
    .notification button:hover {
      color: #e74c3c;
    }
    @keyframes slideIn {
      from {
        transform: translateX(100%);
        opacity: 0;
      }
      to {
        transform: translateX(0);
        opacity: 1;
      }
    }
  `]
})
export class NotificationComponent {
  message = '';

  constructor(private notificationService: NotificationService) {
    this.notificationService.notification$.subscribe(msg => {
      this.message = msg;
    });
  }

  clear(): void {
    this.notificationService.clear();
  }
}
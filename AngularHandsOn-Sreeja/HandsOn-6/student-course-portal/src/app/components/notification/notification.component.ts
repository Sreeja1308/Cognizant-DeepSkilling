import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NotificationService } from '../../services/notification.service';

@Component({
  selector: 'app-notification',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './notification.component.html',
  styleUrl: './notification.component.css',
  providers: [NotificationService] // Component-level provider - creates a new, separate instance scoped to this component
})
export class NotificationComponent {
  message: string = '';

  constructor(private notificationService: NotificationService) {}

  // This creates a new instance of NotificationService scoped to this component
  // Different from the root-level singleton - each NotificationComponent instance gets its own service instance
  // This is useful when you need isolated state per component instance, such as a form wizard with multiple steps

  sendNotification(): void {
    if (this.message.trim()) {
      this.notificationService.addMessage(this.message);
      this.message = '';
    }
  }

  getMessages(): string[] {
    return this.notificationService.getMessages();
  }

  clearMessages(): void {
    this.notificationService.clearMessages();
  }
}
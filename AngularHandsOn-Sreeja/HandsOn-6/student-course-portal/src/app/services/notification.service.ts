import { Injectable } from '@angular/core';

@Injectable()
export class NotificationService {
  private messages: string[] = [];

  addMessage(message: string): void {
    this.messages.push(message);
    console.log('Notification:', message);
  }

  getMessages(): string[] {
    return this.messages;
  }

  clearMessages(): void {
    this.messages = [];
  }
}
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private notificationSubject = new Subject<string>();
  notification$ = this.notificationSubject.asObservable();

  notify(message: string): void {
    this.notificationSubject.next(message);
  }

  clear(): void {
    this.notificationSubject.next('');
  }

  error(message: string): void {
    this.notify(`Error: ${message}`);
  }

  success(message: string): void {
    this.notify(`Success: ${message}`);
  }
}
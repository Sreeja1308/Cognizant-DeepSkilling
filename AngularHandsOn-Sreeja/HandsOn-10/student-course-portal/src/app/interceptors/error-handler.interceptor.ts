import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import { NotificationService } from '../services/notification.service';

export const errorHandlerInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);
  const notificationService = inject(NotificationService);

  return next(req).pipe(
    catchError((error) => {
      if (error.status === 401) {
        router.navigate(['/']);
      } else if (error.status === 500) {
        notificationService.error('Server error occurred');
      } else if (error.status === 0) {
        notificationService.error('Network error - please check your connection');
      }
      return throwError(() => error);
    })
  );
};
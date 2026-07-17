import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';

export const errorHandlerInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);

  return next(req).pipe(
    catchError((error) => {
      console.error('HTTP Error:', error);
      
      if (error.status === 401) {
        // Unauthorized - navigate to home
        router.navigate(['/']);
        alert('Session expired. Please login again.');
      } else if (error.status === 500) {
        // Server error
        alert('Server error occurred. Please try again later.');
      } else if (error.status === 404) {
        // Not found
        console.warn('Resource not found:', error.url);
      }
      
      return throwError(() => error);
    })
  );
};
import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  // Clone the request and add Authorization header
  const authReq = req.clone({
    setHeaders: {
      Authorization: 'Bearer mocktoken-12345'
    }
  });
  return next(authReq);
};
import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const token = localStorage.getItem('token');

  if ((token === undefined || token === null) && state.url !== '/login') {
    router.navigate(['/login']);
    return false;
  } else if (state.url === '/login' && token !== null) {
    router.navigate(['/dashboard']);
    return true;
  }

  return true;
};

import { Routes } from '@angular/router';
import { PagesComponent } from './pages.component';

export const PAGES_ROUTES: Routes = [
  {
    path: '',
    component: PagesComponent,
    children: [
      {
        path: 'home',
        loadChildren: () =>
          import('./home/home.routes').then((m) => m.HOME_ROUTES),
      },
      {
        path: 'messages',
        loadChildren: () =>
          import('./messages/message.routes').then((m) => m.MessageRoutes),
      },
      {
        path: 'partners',
        loadChildren: () =>
          import('./partner/partner.routes').then((m) => m.PARTNERS_ROUTES),
      }
 
    ],
  },
];

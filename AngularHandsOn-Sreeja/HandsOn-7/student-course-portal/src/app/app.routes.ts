import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { StudentProfileComponent } from './pages/student-profile/student-profile.component';
import { CourseDetailComponent } from './pages/course-detail/course-detail.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { CoursesLayoutComponent } from './pages/courses-layout/courses-layout.component';
import { authGuard } from './guards/auth.guard';
import { unsavedChangesGuard } from './guards/unsaved-changes.guard';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { 
    path: 'courses', 
    component: CoursesLayoutComponent,
    children: [
      { path: '', loadComponent: () => import('./pages/course-list/course-list.component').then(m => m.CourseListComponent) },
      { path: ':id', component: CourseDetailComponent }
    ]
  },
  { 
    path: 'profile', 
    component: StudentProfileComponent,
    canActivate: [authGuard]
  },
  // Lazy loading for enrollment forms - using loadComponent for standalone components
  { 
    path: 'enroll', 
    loadComponent: () => import('./pages/enrollment-form/enrollment-form.component').then(m => m.EnrollmentFormComponent)
  },
  { 
    path: 'enroll-reactive', 
    loadComponent: () => import('./pages/reactive-enrollment-form/reactive-enrollment-form.component').then(m => m.ReactiveEnrollmentFormComponent),
    canDeactivate: [unsavedChangesGuard]
  },
  { path: '**', component: NotFoundComponent }
];
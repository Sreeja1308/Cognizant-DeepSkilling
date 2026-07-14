import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CourseCardComponent } from '../../components/course-card/course-card.component';

@Component({
  selector: 'app-course-list',
  standalone: true,
  imports: [CommonModule, CourseCardComponent],
  templateUrl: './course-list.component.html',
  styleUrl: './course-list.component.css'
})
export class CourseListComponent {
  selectedCourseId: number | null = null;
  
  courses = [
    { id: 1, name: 'Data Structures', code: 'CS101', credits: 4 },
    { id: 2, name: 'Algorithms', code: 'CS102', credits: 3 },
    { id: 3, name: 'Database Systems', code: 'CS201', credits: 4 },
    { id: 4, name: 'Web Development', code: 'CS301', credits: 3 },
    { id: 5, name: 'Machine Learning', code: 'CS401', credits: 3 }
  ];

  onEnroll(courseId: number): void {
    console.log('Enrolling in course:', courseId);
    this.selectedCourseId = courseId;
  }
}
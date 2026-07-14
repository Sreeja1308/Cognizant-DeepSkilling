import { Component, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-course-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './course-card.component.html',
  styleUrl: './course-card.component.css'
})
export class CourseCardComponent implements OnChanges {
  @Input() course: { id: number; name: string; code: string; credits: number } | null = null;
  @Output() enrollRequested = new EventEmitter<number>();

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['course'] && this.course) {
      console.log('Course changed - Previous:', changes['course'].previousValue, 'Current:', changes['course'].currentValue);
    }
  }

  onEnroll(): void {
    if (this.course) {
      this.enrollRequested.emit(this.course.id);
    }
  }
}
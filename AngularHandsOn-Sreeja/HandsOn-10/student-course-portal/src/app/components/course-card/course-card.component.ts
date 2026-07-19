import { Component, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HighlightDirective } from '../../directives/highlight.directive';
import { CreditLabelPipe } from '../../pipes/credit-label.pipe';
import { Course } from '../../models/course.model';

@Component({
  selector: 'app-course-card',
  standalone: true,
  imports: [CommonModule, HighlightDirective, CreditLabelPipe],
  templateUrl: './course-card.component.html',
  styleUrls: ['./course-card.component.css']
})
export class CourseCardComponent implements OnChanges {
  @Input() course!: Course;
  @Output() enrollRequested = new EventEmitter<number>();
  @Output() courseClick = new EventEmitter<number>();
  
  isExpanded = false;

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['course']) {
      console.log('Course changed:', changes['course'].previousValue, '->', changes['course'].currentValue);
    }
  }

  get cardClasses(): any {
    return {
      'card--enrolled': this.course?.enrolled || false,
      'card--full': (this.course?.credits || 0) >= 4,
      'expanded': this.isExpanded
    };
  }

  get borderColor(): string {
    switch (this.course?.gradeStatus) {
      case 'passed': return 'green';
      case 'failed': return 'red';
      case 'pending': return 'grey';
      default: return 'black';
    }
  }

  onEnroll(): void {
    this.enrollRequested.emit(this.course.id);
  }

  onCardClick(): void {
    this.courseClick.emit(this.course.id);
  }

  toggleExpand(): void {
    this.isExpanded = !this.isExpanded;
  }
}
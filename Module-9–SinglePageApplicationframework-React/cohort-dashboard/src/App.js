import React from 'react';
import './App.css';
import CohortDetails from './CohortDetails';

function App() {
  const sampleCohorts = [
    {
      id: 'COH-001',
      name: 'React Development Bootcamp',
      startDate: '2026-01-15',
      endDate: '2026-03-15',
      status: 'ongoing',
      totalStudents: 25,
      trainer: 'John Smith'
    },
    {
      id: 'COH-002',
      name: 'Full Stack JavaScript',
      startDate: '2025-10-01',
      endDate: '2025-12-20',
      status: 'completed',
      totalStudents: 30,
      trainer: 'Sarah Johnson'
    },
    {
      id: 'COH-003',
      name: 'Cloud Architecture',
      startDate: '2026-02-01',
      endDate: '2026-04-30',
      status: 'ongoing',
      totalStudents: 18,
      trainer: 'Michael Brown'
    },
    {
      id: 'COH-004',
      name: 'DevOps Fundamentals',
      startDate: '2025-08-15',
      endDate: '2025-11-10',
      status: 'completed',
      totalStudents: 22,
      trainer: 'Emily Davis'
    }
  ];

  return (
    <div className="App">
      <CohortDetails cohorts={sampleCohorts} />
    </div>
  );
}

export default App;
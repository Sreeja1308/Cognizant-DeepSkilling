import React from 'react';
import './App.css';
import CalculateScore from './Components/CalculateScore';

function App() {
  return (
    <div className="App">
      <CalculateScore 
        name="John Doe" 
        school="Springfield High School" 
        total={450} 
        goal={500} 
      />
      <CalculateScore 
        name="Jane Smith" 
        school="Greenwood Academy" 
        total={380} 
        goal={400} 
      />
      <CalculateScore 
        name="Mike Johnson" 
        school="Riverside College" 
        total={275} 
        goal={300} 
      />
    </div>
  );
}

export default App;
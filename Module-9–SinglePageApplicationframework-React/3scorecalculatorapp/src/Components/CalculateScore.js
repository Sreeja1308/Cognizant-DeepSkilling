import React from 'react';
import '../Stylesheets/mystyle.css';

function CalculateScore(props) {
    const average = props.total / props.goal;
    
    return (
        <div className="score-container">
            <h2>Student Score Calculator</h2>
            <div className="student-details">
                <p><strong>Name:</strong> {props.name}</p>
                <p><strong>School:</strong> {props.school}</p>
                <p><strong>Total Score:</strong> {props.total}</p>
                <p><strong>Goal:</strong> {props.goal}</p>
                <p><strong>Average Score:</strong> {average.toFixed(2)*100}</p>
            </div>
        </div>
    );
}

export default CalculateScore;
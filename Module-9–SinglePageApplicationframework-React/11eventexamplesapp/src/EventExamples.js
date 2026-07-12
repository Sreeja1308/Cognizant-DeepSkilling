import React, { Component } from 'react';
import IncrementDecrement from './Components/IncrementDecrement';
import SayWelcome from './Components/SayWelcome';
import SyntheticEvent from './Components/SyntheticEvent';
import CurrencyConverter from './Components/CurrencyConverter';

class EventExamples extends Component {
    render() {
        return (
            <div style={styles.container}>
                <h1 style={styles.header}>🎯 Event Handling Examples</h1>
                
                <IncrementDecrement />
                <SayWelcome />
                <SyntheticEvent />
                
                <div style={styles.converterSection}>
                    <CurrencyConverter />
                </div>
            </div>
        );
    }
}

const styles = {
    container: {
        maxWidth: '800px',
        margin: '40px auto',
        padding: '30px',
        backgroundColor: '#f8f9fa',
        borderRadius: '15px',
        boxShadow: '0 4px 20px rgba(0,0,0,0.1)',
        fontFamily: 'Arial, sans-serif'
    },
    header: {
        textAlign: 'center',
        color: '#2c3e50',
        fontSize: '32px',
        marginBottom: '30px',
        borderBottom: '3px solid #3498db',
        paddingBottom: '15px'
    },
    converterSection: {
        marginTop: '30px',
        paddingTop: '30px',
        borderTop: '2px solid #dee2e6'
    }
};

export default EventExamples;
import React, { Component } from 'react';

class IncrementDecrement extends Component {
    constructor(props) {
        super(props);
        this.state = {
            counter: 0
        };
        this.increment = this.increment.bind(this);
        this.decrement = this.decrement.bind(this);
        this.sayHello = this.sayHello.bind(this);
    }

    increment() {
        this.setState((prevState) => ({
            counter: prevState.counter + 1
        }));
    }

    sayHello() {
        alert('Hello! Welcome to Event Handling in React!');
    }

    handleIncrement = () => {
        this.increment();
        this.sayHello();
    }

    decrement() {
        this.setState((prevState) => ({
            counter: prevState.counter - 1
        }));
    }

    render() {
        return (
            <div style={styles.container}>
                <h2 style={styles.title}>1. Counter with Multiple Methods</h2>
                <div style={styles.counterContainer}>
                    <span style={styles.counterDisplay}>Counter: {this.state.counter}</span>
                    <div style={styles.buttonGroup}>
                        <button 
                            onClick={this.handleIncrement} 
                            style={styles.buttonPrimary}
                        >
                            ➕ Increment (Calls 2 methods)
                        </button>
                        <button 
                            onClick={this.decrement} 
                            style={styles.buttonDanger}
                        >
                            ➖ Decrement
                        </button>
                    </div>
                    <p style={styles.hint}>Click Increment to increase counter and say Hello!</p>
                </div>
            </div>
        );
    }
}

const styles = {
    container: {
        backgroundColor: 'white',
        padding: '20px',
        marginBottom: '25px',
        borderRadius: '10px',
        boxShadow: '0 2px 5px rgba(0,0,0,0.05)'
    },
    title: {
        color: '#495057',
        fontSize: '20px',
        marginBottom: '15px',
        borderLeft: '4px solid #007bff',
        paddingLeft: '10px'
    },
    counterContainer: {
        textAlign: 'center',
        padding: '10px'
    },
    counterDisplay: {
        fontSize: '28px',
        fontWeight: '700',
        color: '#2c3e50',
        display: 'block',
        marginBottom: '15px'
    },
    buttonGroup: {
        display: 'flex',
        gap: '10px',
        justifyContent: 'center',
        marginBottom: '10px'
    },
    buttonPrimary: {
        backgroundColor: '#007bff',
        color: 'white',
        border: 'none',
        padding: '12px 25px',
        fontSize: '16px',
        borderRadius: '8px',
        cursor: 'pointer',
        transition: 'all 0.3s'
    },
    buttonDanger: {
        backgroundColor: '#dc3545',
        color: 'white',
        border: 'none',
        padding: '12px 25px',
        fontSize: '16px',
        borderRadius: '8px',
        cursor: 'pointer',
        transition: 'all 0.3s'
    },
    hint: {
        color: '#6c757d',
        fontSize: '14px',
        marginTop: '10px',
        fontStyle: 'italic'
    }
};

export default IncrementDecrement;
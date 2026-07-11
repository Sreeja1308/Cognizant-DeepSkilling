import React, { Component } from 'react';

class SayWelcome extends Component {
    constructor(props) {
        super(props);
        this.state = {
            welcomeMessage: ''
        };
        this.sayWelcome = this.sayWelcome.bind(this);
    }

    sayWelcome(message) {
        this.setState({
            welcomeMessage: message
        });
        alert(`Welcome message triggered: ${message}`);
    }

    render() {
        return (
            <div style={styles.container}>
                <h2 style={styles.title}>2. Say Welcome with Argument</h2>
                <div style={styles.welcomeContainer}>
                    <button 
                        onClick={() => this.sayWelcome('Welcome to React Events!')} 
                        style={styles.buttonSuccess}
                    >
                        👋 Say Welcome
                    </button>
                    {this.state.welcomeMessage && (
                        <p style={styles.welcomeMessage}>{this.state.welcomeMessage}</p>
                    )}
                    <p style={styles.hint}>Click to trigger welcome message with argument</p>
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
        borderLeft: '4px solid #28a745',
        paddingLeft: '10px'
    },
    welcomeContainer: {
        textAlign: 'center',
        padding: '10px'
    },
    buttonSuccess: {
        backgroundColor: '#28a745',
        color: 'white',
        border: 'none',
        padding: '12px 25px',
        fontSize: '16px',
        borderRadius: '8px',
        cursor: 'pointer',
        transition: 'all 0.3s'
    },
    welcomeMessage: {
        color: '#28a745',
        fontSize: '18px',
        fontWeight: '600',
        marginTop: '10px'
    },
    hint: {
        color: '#6c757d',
        fontSize: '14px',
        marginTop: '10px',
        fontStyle: 'italic'
    }
};

export default SayWelcome;
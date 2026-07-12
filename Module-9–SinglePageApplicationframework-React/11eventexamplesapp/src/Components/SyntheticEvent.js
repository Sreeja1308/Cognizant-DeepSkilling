import React, { Component } from 'react';

class SyntheticEvent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            clickMessage: ''
        };
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick(event) {
        this.setState({
            clickMessage: 'I was clicked'
        });
        alert('I was clicked');
        console.log('Synthetic Event:', event);
        console.log('Event Type:', event.type);
        console.log('Target:', event.target);
        console.log('Current Target:', event.currentTarget);
    }

    render() {
        return (
            <div style={styles.container}>
                <h2 style={styles.title}>3. Synthetic Event Handler</h2>
                <div style={styles.syntheticContainer}>
                    <button 
                        onClick={this.handleClick} 
                        style={styles.buttonInfo}
                    >
                        🖱️ Click Me
                    </button>
                    {this.state.clickMessage && (
                        <p style={styles.clickMessage}>{this.state.clickMessage}</p>
                    )}
                    <p style={styles.hint}>Click to see synthetic event in action (check console)</p>
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
        borderLeft: '4px solid #17a2b8',
        paddingLeft: '10px'
    },
    syntheticContainer: {
        textAlign: 'center',
        padding: '10px'
    },
    buttonInfo: {
        backgroundColor: '#17a2b8',
        color: 'white',
        border: 'none',
        padding: '12px 25px',
        fontSize: '16px',
        borderRadius: '8px',
        cursor: 'pointer',
        transition: 'all 0.3s'
    },
    clickMessage: {
        color: '#17a2b8',
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

export default SyntheticEvent;
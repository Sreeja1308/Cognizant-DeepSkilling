import React, { Component } from 'react';

class CountPeople extends Component {
    constructor(props) {
        super(props);
        this.state = {
            entrycount: 0,
            exitcount: 0
        };
        this.updateEntry = this.updateEntry.bind(this);
        this.updateExit = this.updateExit.bind(this);
    }

    updateEntry() {
        this.setState((prevState) => ({
            entrycount: prevState.entrycount + 1
        }));
    }

    updateExit() {
        this.setState((prevState) => ({
            exitcount: prevState.exitcount + 1
        }));
    }

    render() {
        return (
            <div style={styles.container}>
                <h1 style={styles.header}>🏬 Mall People Counter</h1>
                
                <div style={styles.counterContainer}>
                    <div style={styles.counterBox}>
                        <h2 style={styles.counterTitle}>People Inside</h2>
                        <div style={styles.counterDisplay}>
                            <span style={styles.counterNumber}>
                                {this.state.entrycount - this.state.exitcount}
                            </span>
                        </div>
                    </div>
                    
                    <div style={styles.statsContainer}>
                        <div style={styles.statsBox}>
                            <h3 style={styles.statsTitle}>Entry Count</h3>
                            <p style={styles.statsNumber}>{this.state.entrycount}</p>
                        </div>
                        <div style={styles.statsBox}>
                            <h3 style={styles.statsTitle}>Exit Count</h3>
                            <p style={styles.statsNumber}>{this.state.exitcount}</p>
                        </div>
                    </div>
                    
                    <div style={styles.buttonContainer}>
                        <button 
                            onClick={this.updateEntry} 
                            style={styles.entryButton}
                            onMouseEnter={(e) => e.target.style.transform = 'scale(1.05)'}
                            onMouseLeave={(e) => e.target.style.transform = 'scale(1)'}
                        >
                            🚪 Entry
                        </button>
                        <button 
                            onClick={this.updateExit} 
                            style={styles.exitButton}
                            onMouseEnter={(e) => e.target.style.transform = 'scale(1.05)'}
                            onMouseLeave={(e) => e.target.style.transform = 'scale(1)'}
                        >
                            🚶 Exit
                        </button>
                    </div>
                    
                    <button 
                        onClick={() => this.setState({ entrycount: 0, exitcount: 0 })} 
                        style={styles.resetButton}
                    >
                        Reset Counters
                    </button>
                </div>
            </div>
        );
    }
}

const styles = {
    container: {
        maxWidth: '600px',
        margin: '50px auto',
        padding: '30px',
        backgroundColor: '#f8f9fa',
        borderRadius: '20px',
        boxShadow: '0 10px 30px rgba(0,0,0,0.1)',
        textAlign: 'center',
        fontFamily: 'Arial, sans-serif'
    },
    header: {
        color: '#2c3e50',
        fontSize: '32px',
        marginBottom: '30px',
        borderBottom: '3px solid #3498db',
        paddingBottom: '15px'
    },
    counterContainer: {
        display: 'flex',
        flexDirection: 'column',
        gap: '25px'
    },
    counterBox: {
        backgroundColor: '#ffffff',
        borderRadius: '15px',
        padding: '25px',
        boxShadow: '0 4px 10px rgba(0,0,0,0.05)'
    },
    counterTitle: {
        color: '#495057',
        fontSize: '20px',
        marginBottom: '15px'
    },
    counterDisplay: {
        backgroundColor: '#e9ecef',
        borderRadius: '10px',
        padding: '15px',
        margin: '10px 0'
    },
    counterNumber: {
        fontSize: '48px',
        fontWeight: '700',
        color: '#3498db'
    },
    statsContainer: {
        display: 'flex',
        justifyContent: 'space-around',
        gap: '20px'
    },
    statsBox: {
        backgroundColor: '#ffffff',
        borderRadius: '10px',
        padding: '15px',
        flex: '1',
        boxShadow: '0 2px 5px rgba(0,0,0,0.05)'
    },
    statsTitle: {
        color: '#6c757d',
        fontSize: '14px',
        marginBottom: '5px'
    },
    statsNumber: {
        fontSize: '28px',
        fontWeight: '600',
        color: '#2c3e50',
        margin: '5px 0'
    },
    buttonContainer: {
        display: 'flex',
        gap: '15px',
        justifyContent: 'center'
    },
    entryButton: {
        backgroundColor: '#28a745',
        color: 'white',
        border: 'none',
        padding: '15px 40px',
        fontSize: '18px',
        fontWeight: '600',
        borderRadius: '10px',
        cursor: 'pointer',
        transition: 'all 0.3s',
        flex: '1'
    },
    exitButton: {
        backgroundColor: '#dc3545',
        color: 'white',
        border: 'none',
        padding: '15px 40px',
        fontSize: '18px',
        fontWeight: '600',
        borderRadius: '10px',
        cursor: 'pointer',
        transition: 'all 0.3s',
        flex: '1'
    },
    resetButton: {
        backgroundColor: '#6c757d',
        color: 'white',
        border: 'none',
        padding: '10px 20px',
        fontSize: '14px',
        fontWeight: '500',
        borderRadius: '8px',
        cursor: 'pointer',
        transition: 'all 0.3s',
        marginTop: '10px',
        alignSelf: 'center'
    }
};

export default CountPeople;
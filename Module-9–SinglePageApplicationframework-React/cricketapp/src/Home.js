import React, { Component } from 'react';
import ListofPlayers from './ListofPlayers';
import IndianPlayers from './IndianPlayers';

class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {
            flag: true
        };
        this.toggleFlag = this.toggleFlag.bind(this);
    }

    toggleFlag() {
        this.setState((prevState) => ({
            flag: !prevState.flag
        }));
    }

    render() {
        return (
            <div style={styles.container}>
                <div style={styles.toggleContainer}>
                    <h1 style={styles.header}>🏏 Cricket Application</h1>
                    <div style={styles.buttonContainer}>
                        <button 
                            onClick={this.toggleFlag} 
                            style={styles.toggleButton}
                        >
                            {this.state.flag ? 'Show Indian Players' : 'Show Player List'}
                        </button>
                        <span style={styles.currentView}>
                            Current View: {this.state.flag ? 'Player List' : 'Indian Players'}
                        </span>
                    </div>
                    <div style={styles.viewContainer}>
                        {this.state.flag ? <ListofPlayers /> : <IndianPlayers />}
                    </div>
                </div>
            </div>
        );
    }
}

const styles = {
    container: {
        maxWidth: '900px',
        margin: '0 auto',
        padding: '20px',
        fontFamily: 'Arial, sans-serif'
    },
    toggleContainer: {
        textAlign: 'center'
    },
    header: {
        color: '#2c3e50',
        fontSize: '36px',
        marginBottom: '20px'
    },
    buttonContainer: {
        marginBottom: '20px',
        padding: '15px',
        backgroundColor: '#e9ecef',
        borderRadius: '10px'
    },
    toggleButton: {
        backgroundColor: '#007bff',
        color: 'white',
        border: 'none',
        padding: '12px 30px',
        fontSize: '16px',
        fontWeight: '600',
        borderRadius: '8px',
        cursor: 'pointer',
        transition: 'all 0.3s',
        marginRight: '15px'
    },
    currentView: {
        fontSize: '16px',
        color: '#495057',
        fontWeight: '500'
    },
    viewContainer: {
        marginTop: '20px'
    }
};

export default Home;
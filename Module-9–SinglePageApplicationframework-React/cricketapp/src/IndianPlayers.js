import React, { Component } from 'react';

class IndianPlayers extends Component {
    constructor(props) {
        super(props);
        // Declaring arrays using ES6
        this.T20players = ['Virat Kohli', 'Rohit Sharma', 'KL Rahul', 'Suryakumar Yadav', 'Rishabh Pant'];
        this.RanjiTrophyPlayers = ['Cheteshwar Pujara', 'Ajinkya Rahane', 'Ravichandran Ashwin', 'Shubman Gill', 'Mayank Agarwal'];
        
        // Merging arrays using ES6 spread operator
        this.allPlayers = [...this.T20players, ...this.RanjiTrophyPlayers];
    }

    render() {
        // Using destructuring to get players
        const [firstPlayer, secondPlayer, ...remainingPlayers] = this.allPlayers;
        
        // Separating odd and even index players using destructuring
        const oddTeamPlayers = this.allPlayers.filter((player, index) => index % 2 !== 0);
        const evenTeamPlayers = this.allPlayers.filter((player, index) => index % 2 === 0);

        return (
            <div style={styles.container}>
                <h2 style={styles.header}>🇮🇳 Indian Cricket Players</h2>
                
                <div style={styles.section}>
                    <h3 style={styles.subHeader}>T20 Players ({this.T20players.length})</h3>
                    <div style={styles.listContainer}>
                        {this.T20players.map((player, index) => (
                            <div key={index} style={styles.playerItem}>
                                {player}
                            </div>
                        ))}
                    </div>
                </div>

                <div style={styles.section}>
                    <h3 style={styles.subHeader}>Ranji Trophy Players ({this.RanjiTrophyPlayers.length})</h3>
                    <div style={styles.listContainer}>
                        {this.RanjiTrophyPlayers.map((player, index) => (
                            <div key={index} style={styles.playerItem}>
                                {player}
                            </div>
                        ))}
                    </div>
                </div>

                <div style={styles.section}>
                    <h3 style={styles.subHeader}>Merged Players (All Indian Players)</h3>
                    <div style={styles.listContainer}>
                        {this.allPlayers.map((player, index) => (
                            <div key={index} style={styles.playerItem}>
                                {player}
                            </div>
                        ))}
                    </div>
                </div>

                <div style={styles.section}>
                    <h3 style={styles.subHeader}>Even Team Players (Index: 0, 2, 4...)</h3>
                    <div style={styles.listContainer}>
                        {evenTeamPlayers.map((player, index) => (
                            <div key={index} style={styles.evenTeamItem}>
                                {player}
                            </div>
                        ))}
                    </div>
                </div>

                <div style={styles.section}>
                    <h3 style={styles.subHeader}>Odd Team Players (Index: 1, 3, 5...)</h3>
                    <div style={styles.listContainer}>
                        {oddTeamPlayers.map((player, index) => (
                            <div key={index} style={styles.oddTeamItem}>
                                {player}
                            </div>
                        ))}
                    </div>
                </div>

                <div style={styles.section}>
                    <h3 style={styles.subHeader}>Destructuring Example</h3>
                    <div style={styles.destructuringBox}>
                        <p><strong>First Player:</strong> {firstPlayer}</p>
                        <p><strong>Second Player:</strong> {secondPlayer}</p>
                        <p><strong>Remaining Players:</strong> {remainingPlayers.join(', ')}</p>
                    </div>
                </div>
            </div>
        );
    }
}

const styles = {
    container: {
        maxWidth: '700px',
        margin: '20px auto',
        padding: '20px',
        backgroundColor: '#f8f9fa',
        borderRadius: '15px',
        boxShadow: '0 4px 15px rgba(0,0,0,0.1)'
    },
    header: {
        textAlign: 'center',
        color: '#2c3e50',
        borderBottom: '3px solid #3498db',
        paddingBottom: '10px',
        marginBottom: '25px'
    },
    section: {
        marginBottom: '25px'
    },
    subHeader: {
        color: '#495057',
        fontSize: '18px',
        marginBottom: '10px',
        paddingLeft: '10px',
        borderLeft: '4px solid #28a745'
    },
    listContainer: {
        backgroundColor: 'white',
        borderRadius: '8px',
        padding: '10px',
        boxShadow: '0 2px 5px rgba(0,0,0,0.05)'
    },
    playerItem: {
        padding: '8px 15px',
        borderBottom: '1px solid #e9ecef',
        color: '#2c3e50'
    },
    evenTeamItem: {
        padding: '8px 15px',
        borderBottom: '1px solid #e9ecef',
        backgroundColor: '#d4edda',
        color: '#155724'
    },
    oddTeamItem: {
        padding: '8px 15px',
        borderBottom: '1px solid #e9ecef',
        backgroundColor: '#cce5ff',
        color: '#004085'
    },
    destructuringBox: {
        backgroundColor: 'white',
        borderRadius: '8px',
        padding: '15px',
        border: '2px solid #3498db'
    }
};

export default IndianPlayers;
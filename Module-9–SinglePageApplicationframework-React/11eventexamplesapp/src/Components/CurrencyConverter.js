import React, { Component } from 'react';

class CurrencyConverter extends Component {
    constructor(props) {
        super(props);
        this.state = {
            rupees: '',
            euros: '',
            conversionRate: 0.011
        };
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleRupeesChange = this.handleRupeesChange.bind(this);
    }

    handleRupeesChange(event) {
        this.setState({
            rupees: event.target.value
        });
    }

    handleSubmit(event) {
        event.preventDefault();
        const rupees = parseFloat(this.state.rupees);
        
        if (isNaN(rupees) || rupees < 0) {
            alert('Please enter a valid amount in Rupees');
            return;
        }

        const euros = rupees * this.state.conversionRate;
        this.setState({
            euros: euros.toFixed(2)
        });
        
        console.log('Conversion Event:', event);
        console.log('Rupees:', rupees);
        console.log('Euros:', euros.toFixed(2));
    }

    handleReset = () => {
        this.setState({
            rupees: '',
            euros: ''
        });
    }

    render() {
        return (
            <div style={styles.container}>
                <h2 style={styles.header}>💰 Currency Converter</h2>
                <p style={styles.subHeader}>Convert Indian Rupees (INR) to Euro (EUR)</p>
                
                <form onSubmit={this.handleSubmit} style={styles.form}>
                    <div style={styles.inputGroup}>
                        <label style={styles.label}>Amount in Rupees (₹):</label>
                        <input
                            type="number"
                            value={this.state.rupees}
                            onChange={this.handleRupeesChange}
                            placeholder="Enter amount in INR"
                            style={styles.input}
                            min="0"
                            step="1"
                        />
                    </div>
                    
                    <div style={styles.buttonGroup}>
                        <button 
                            type="submit" 
                            style={styles.convertButton}
                        >
                            🔄 Convert to Euro
                        </button>
                        <button 
                            type="button" 
                            onClick={this.handleReset}
                            style={styles.resetButton}
                        >
                            🔄 Reset
                        </button>
                    </div>
                    
                    {this.state.euros && (
                        <div style={styles.resultContainer}>
                            <h3 style={styles.resultTitle}>Converted Amount:</h3>
                            <p style={styles.result}>
                                ₹{this.state.rupees} = €{this.state.euros}
                            </p>
                            <p style={styles.conversionRate}>
                                Conversion Rate: 1 INR = {this.state.conversionRate} EUR
                            </p>
                        </div>
                    )}
                </form>
            </div>
        );
    }
}

const styles = {
    container: {
        backgroundColor: 'white',
        padding: '30px',
        borderRadius: '15px',
        boxShadow: '0 2px 5px rgba(0,0,0,0.05)',
        maxWidth: '500px',
        margin: '0 auto'
    },
    header: {
        textAlign: 'center',
        color: '#2c3e50',
        fontSize: '28px',
        marginBottom: '5px'
    },
    subHeader: {
        textAlign: 'center',
        color: '#6c757d',
        fontSize: '14px',
        marginBottom: '25px'
    },
    form: {
        display: 'flex',
        flexDirection: 'column',
        gap: '20px'
    },
    inputGroup: {
        display: 'flex',
        flexDirection: 'column',
        gap: '8px'
    },
    label: {
        fontSize: '16px',
        fontWeight: '500',
        color: '#495057'
    },
    input: {
        padding: '12px 15px',
        fontSize: '16px',
        borderRadius: '8px',
        border: '2px solid #ced4da',
        transition: 'border-color 0.3s',
        outline: 'none'
    },
    buttonGroup: {
        display: 'flex',
        gap: '10px',
        justifyContent: 'center'
    },
    convertButton: {
        backgroundColor: '#28a745',
        color: 'white',
        border: 'none',
        padding: '12px 30px',
        fontSize: '16px',
        fontWeight: '600',
        borderRadius: '8px',
        cursor: 'pointer',
        transition: 'all 0.3s',
        flex: '1'
    },
    resetButton: {
        backgroundColor: '#6c757d',
        color: 'white',
        border: 'none',
        padding: '12px 20px',
        fontSize: '16px',
        fontWeight: '600',
        borderRadius: '8px',
        cursor: 'pointer',
        transition: 'all 0.3s'
    },
    resultContainer: {
        marginTop: '15px',
        padding: '20px',
        backgroundColor: '#e9ecef',
        borderRadius: '10px',
        textAlign: 'center'
    },
    resultTitle: {
        color: '#495057',
        fontSize: '16px',
        marginBottom: '10px'
    },
    result: {
        fontSize: '24px',
        fontWeight: '700',
        color: '#28a745',
        margin: '10px 0'
    },
    conversionRate: {
        fontSize: '14px',
        color: '#6c757d',
        marginTop: '5px'
    }
};

export default CurrencyConverter;
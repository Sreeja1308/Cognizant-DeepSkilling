import React, { Component } from 'react';

class ComplaintRegister extends Component {
    constructor(props) {
        super(props);
        this.state = {
            employeeName: '',
            complaint: '',
            department: '',
            priority: 'medium',
            submittedComplaints: [],
            referenceNumber: ''
        };
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleReset = this.handleReset.bind(this);
    }

   
    handleInputChange(event) {
        const target = event.target;
        const name = target.name;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        
        this.setState({
            [name]: value
        });
    }

    generateReferenceNumber() {
        const timestamp = Date.now().toString().slice(-6);
        const random = Math.floor(Math.random() * 1000).toString().padStart(3, '0');
        const prefix = 'CMP';
        return `${prefix}-${timestamp}-${random}`;
    }

    handleSubmit(event) {
        event.preventDefault(); 
        
        if (!this.state.employeeName.trim()) {
            alert('⚠️ Please enter your name');
            return;
        }
        
        if (!this.state.complaint.trim()) {
            alert('⚠️ Please describe your complaint');
            return;
        }

        const referenceNumber = this.generateReferenceNumber();
        
        const newComplaint = {
            id: Date.now(),
            employeeName: this.state.employeeName,
            complaint: this.state.complaint,
            department: this.state.department || 'Not Specified',
            priority: this.state.priority,
            referenceNumber: referenceNumber,
            status: 'Pending',
            date: new Date().toLocaleDateString(),
            time: new Date().toLocaleTimeString()
        };

        this.setState((prevState) => ({
            submittedComplaints: [...prevState.submittedComplaints, newComplaint],
            referenceNumber: referenceNumber,
            
            employeeName: '',
            complaint: '',
            department: '',
            priority: 'medium'
        }));

        alert(
            `✅ Complaint Registered Successfully!\n\n` +
            `Reference Number: ${referenceNumber}\n` +
            `Employee: ${newComplaint.employeeName}\n` +
            `Department: ${newComplaint.department}\n` +
            `Priority: ${newComplaint.priority}\n` +
            `Date: ${newComplaint.date}\n\n` +
            `Please save this reference number for future follow-ups.`
        );

        console.log('New Complaint Registered:', newComplaint);
    }

    handleReset() {
        this.setState({
            employeeName: '',
            complaint: '',
            department: '',
            priority: 'medium'
        });
    }

    getPriorityColor(priority) {
        switch(priority) {
            case 'high':
                return '#dc3545';
            case 'medium':
                return '#ffc107';
            case 'low':
                return '#28a745';
            default:
                return '#6c757d';
        }
    }

    render() {
        return (
            <div style={styles.container}>
                <h1 style={styles.header}>📝 Complaint Registration</h1>
                <p style={styles.subHeader}>Raise your complaint and get it resolved</p>
                
                <form onSubmit={this.handleSubmit} style={styles.form}>
                    <div style={styles.formGroup}>
                        <label style={styles.label}>
                            Employee Name: <span style={styles.required}>*</span>
                        </label>
                        <input
                            type="text"
                            name="employeeName"
                            value={this.state.employeeName}
                            onChange={this.handleInputChange}
                            placeholder="Enter your full name"
                            style={styles.input}
                            required
                        />
                    </div>

                    <div style={styles.formGroup}>
                        <label style={styles.label}>
                            Department:
                        </label>
                        <select
                            name="department"
                            value={this.state.department}
                            onChange={this.handleInputChange}
                            style={styles.select}
                        >
                            <option value="">Select Department</option>
                            <option value="Engineering">Engineering</option>
                            <option value="Human Resources">Human Resources</option>
                            <option value="Finance">Finance</option>
                            <option value="Marketing">Marketing</option>
                            <option value="Sales">Sales</option>
                            <option value="IT Support">IT Support</option>
                            <option value="Operations">Operations</option>
                            <option value="Other">Other</option>
                        </select>
                    </div>

                    <div style={styles.formGroup}>
                        <label style={styles.label}>
                            Priority:
                        </label>
                        <div style={styles.radioGroup}>
                            <label style={styles.radioLabel}>
                                <input
                                    type="radio"
                                    name="priority"
                                    value="high"
                                    checked={this.state.priority === 'high'}
                                    onChange={this.handleInputChange}
                                />
                                🔴 High
                            </label>
                            <label style={styles.radioLabel}>
                                <input
                                    type="radio"
                                    name="priority"
                                    value="medium"
                                    checked={this.state.priority === 'medium'}
                                    onChange={this.handleInputChange}
                                />
                                🟡 Medium
                            </label>
                            <label style={styles.radioLabel}>
                                <input
                                    type="radio"
                                    name="priority"
                                    value="low"
                                    checked={this.state.priority === 'low'}
                                    onChange={this.handleInputChange}
                                />
                                🟢 Low
                            </label>
                        </div>
                    </div>

                    <div style={styles.formGroup}>
                        <label style={styles.label}>
                            Complaint Description: <span style={styles.required}>*</span>
                        </label>
                        <textarea
                            name="complaint"
                            value={this.state.complaint}
                            onChange={this.handleInputChange}
                            placeholder="Describe your complaint in detail..."
                            rows="5"
                            style={styles.textarea}
                            required
                        />
                    </div>

                    <div style={styles.buttonGroup}>
                        <button 
                            type="submit" 
                            style={styles.submitButton}
                            onMouseEnter={(e) => e.target.style.transform = 'scale(1.02)'}
                            onMouseLeave={(e) => e.target.style.transform = 'scale(1)'}
                        >
                            📤 Submit Complaint
                        </button>
                        <button 
                            type="button" 
                            onClick={this.handleReset}
                            style={styles.resetButton}
                        >
                            🔄 Reset Form
                        </button>
                    </div>
                </form>

               
                {this.state.submittedComplaints.length > 0 && (
                    <div style={styles.recentContainer}>
                        <h2 style={styles.recentHeader}>📋 Recent Complaints</h2>
                        <div style={styles.complaintList}>
                            {this.state.submittedComplaints.slice(-3).reverse().map((complaint) => (
                                <div key={complaint.id} style={styles.complaintCard}>
                                    <div style={styles.complaintHeader}>
                                        <span style={styles.complaintRef}>
                                            {complaint.referenceNumber}
                                        </span>
                                        <span style={{
                                            ...styles.statusBadge,
                                            backgroundColor: complaint.status === 'Pending' ? '#ffc107' : '#28a745'
                                        }}>
                                            {complaint.status}
                                        </span>
                                    </div>
                                    <div style={styles.complaintDetails}>
                                        <p><strong>Employee:</strong> {complaint.employeeName}</p>
                                        <p><strong>Department:</strong> {complaint.department}</p>
                                        <p style={styles.complaintText}>
                                            <strong>Complaint:</strong> {complaint.complaint}
                                        </p>
                                        <div style={styles.complaintMeta}>
                                            <span style={{
                                                ...styles.priorityBadge,
                                                backgroundColor: this.getPriorityColor(complaint.priority)
                                            }}>
                                                {complaint.priority}
                                            </span>
                                            <span style={styles.dateTime}>
                                                {complaint.date} {complaint.time}
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </div>
                )}

                {this.state.referenceNumber && (
                    <div style={styles.lastReference}>
                        <p style={styles.referenceText}>
                            Last Complaint Reference: <strong>{this.state.referenceNumber}</strong>
                        </p>
                    </div>
                )}
            </div>
        );
    }
}

const styles = {
    container: {
        maxWidth: '800px',
        margin: '40px auto',
        padding: '30px',
        backgroundColor: '#ffffff',
        borderRadius: '20px',
        boxShadow: '0 4px 20px rgba(0,0,0,0.1)',
        fontFamily: 'Arial, sans-serif'
    },
    header: {
        textAlign: 'center',
        color: '#2c3e50',
        fontSize: '32px',
        marginBottom: '5px'
    },
    subHeader: {
        textAlign: 'center',
        color: '#6c757d',
        marginBottom: '30px'
    },
    form: {
        display: 'flex',
        flexDirection: 'column',
        gap: '20px'
    },
    formGroup: {
        display: 'flex',
        flexDirection: 'column',
        gap: '8px'
    },
    label: {
        fontSize: '16px',
        fontWeight: '500',
        color: '#495057'
    },
    required: {
        color: '#dc3545'
    },
    input: {
        padding: '12px 15px',
        fontSize: '16px',
        border: '2px solid #ced4da',
        borderRadius: '8px',
        transition: 'border-color 0.3s',
        outline: 'none'
    },
    textarea: {
        padding: '12px 15px',
        fontSize: '16px',
        border: '2px solid #ced4da',
        borderRadius: '8px',
        resize: 'vertical',
        transition: 'border-color 0.3s',
        outline: 'none',
        fontFamily: 'inherit'
    },
    select: {
        padding: '12px 15px',
        fontSize: '16px',
        border: '2px solid #ced4da',
        borderRadius: '8px',
        outline: 'none',
        backgroundColor: 'white'
    },
    radioGroup: {
        display: 'flex',
        gap: '20px',
        padding: '10px 0'
    },
    radioLabel: {
        display: 'flex',
        alignItems: 'center',
        gap: '5px',
        cursor: 'pointer'
    },
    buttonGroup: {
        display: 'flex',
        gap: '15px',
        marginTop: '10px'
    },
    submitButton: {
        backgroundColor: '#007bff',
        color: 'white',
        border: 'none',
        padding: '14px 30px',
        fontSize: '18px',
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
        padding: '14px 30px',
        fontSize: '18px',
        fontWeight: '600',
        borderRadius: '8px',
        cursor: 'pointer',
        transition: 'all 0.3s'
    },
    recentContainer: {
        marginTop: '40px',
        paddingTop: '30px',
        borderTop: '2px solid #dee2e6'
    },
    recentHeader: {
        color: '#2c3e50',
        fontSize: '24px',
        marginBottom: '20px'
    },
    complaintList: {
        display: 'flex',
        flexDirection: 'column',
        gap: '15px'
    },
    complaintCard: {
        backgroundColor: '#f8f9fa',
        padding: '20px',
        borderRadius: '10px',
        borderLeft: '4px solid #007bff'
    },
    complaintHeader: {
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        marginBottom: '10px',
        paddingBottom: '10px',
        borderBottom: '1px solid #dee2e6'
    },
    complaintRef: {
        fontWeight: '600',
        color: '#007bff',
        fontSize: '16px'
    },
    statusBadge: {
        padding: '4px 12px',
        borderRadius: '20px',
        color: 'white',
        fontSize: '12px',
        fontWeight: '600'
    },
    complaintDetails: {
        fontSize: '14px'
    },
    complaintText: {
        marginTop: '8px',
        padding: '10px',
        backgroundColor: 'white',
        borderRadius: '5px'
    },
    complaintMeta: {
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        marginTop: '10px'
    },
    priorityBadge: {
        padding: '3px 10px',
        borderRadius: '20px',
        color: 'white',
        fontSize: '12px',
        fontWeight: '600',
        textTransform: 'capitalize'
    },
    dateTime: {
        color: '#6c757d',
        fontSize: '12px'
    },
    lastReference: {
        marginTop: '20px',
        padding: '15px',
        backgroundColor: '#e9ecef',
        borderRadius: '8px',
        textAlign: 'center'
    },
    referenceText: {
        margin: '0',
        color: '#495057'
    }
};

export default ComplaintRegister;
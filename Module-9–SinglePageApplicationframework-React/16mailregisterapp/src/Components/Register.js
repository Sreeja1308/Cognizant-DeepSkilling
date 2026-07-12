import React, { Component } from 'react';

class Register extends Component {
    constructor(props) {
        super(props);
        this.state = {
            name: '',
            email: '',
            password: '',
            confirmPassword: '',
            errors: {
                name: '',
                email: '',
                password: '',
                confirmPassword: ''
            },
            showPassword: false
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.validateField = this.validateField.bind(this);
        this.validateForm = this.validateForm.bind(this);
        this.togglePasswordVisibility = this.togglePasswordVisibility.bind(this);
    }

    validateField(name, value) {
        let error = '';
        
        switch(name) {
            case 'name':
                if (!value.trim()) {
                    error = 'Name is required';
                } else if (value.trim().length < 5) {
                    error = 'Name must be at least 5 characters long';
                } else if (!/^[a-zA-Z\s]+$/.test(value.trim())) {
                    error = 'Name should contain only letters and spaces';
                }
                break;
                
            case 'email':
                if (!value.trim()) {
                    error = 'Email is required';
                } else if (!value.includes('@')) {
                    error = 'Email must contain @ symbol';
                } else if (!value.includes('.')) {
                    error = 'Email must contain a dot (.)';
                } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value.trim())) {
                    error = 'Please enter a valid email address';
                }
                break;
                
            case 'password':
                if (!value) {
                    error = 'Password is required';
                } else if (value.length < 8) {
                    error = 'Password must be at least 8 characters long';
                } else if (!/[A-Z]/.test(value)) {
                    error = 'Password must contain at least one uppercase letter';
                } else if (!/[a-z]/.test(value)) {
                    error = 'Password must contain at least one lowercase letter';
                } else if (!/[0-9]/.test(value)) {
                    error = 'Password must contain at least one number';
                } else if (!/[!@#$%^&*(),.?":{}|<>]/.test(value)) {
                    error = 'Password must contain at least one special character';
                }
                break;
                
            case 'confirmPassword':
                if (!value) {
                    error = 'Please confirm your password';
                } else if (value !== this.state.password) {
                    error = 'Passwords do not match';
                }
                break;
                
            default:
                break;
        }
        
        return error;
    }

    handleChange(event) {
        const { name, value } = event.target;
        
        this.setState({
            [name]: value
        }, () => {
            const error = this.validateField(name, value);
            this.setState(prevState => ({
                errors: {
                    ...prevState.errors,
                    [name]: error
                }
            }));
        });
    }

    validateForm() {
        const { name, email, password, confirmPassword } = this.state;
        const errors = {
            name: this.validateField('name', name),
            email: this.validateField('email', email),
            password: this.validateField('password', password),
            confirmPassword: this.validateField('confirmPassword', confirmPassword)
        };
        
        this.setState({ errors });
        
        return !Object.values(errors).some(error => error !== '');
    }

    handleSubmit(event) {
        event.preventDefault();
        
        if (this.validateForm()) {
            alert(
                `✅ Registration Successful!\n\n` +
                `Name: ${this.state.name}\n` +
                `Email: ${this.state.email}\n\n` +
                `Welcome to Mail Register App!`
            );
            
            console.log('User Registered:', {
                name: this.state.name,
                email: this.state.email,
                password: '********' // Don't log actual password
            });
            
            this.resetForm();
        } else {
            const firstErrorField = document.querySelector('.error-input');
            if (firstErrorField) {
                firstErrorField.focus();
            }
        }
    }

    resetForm() {
        this.setState({
            name: '',
            email: '',
            password: '',
            confirmPassword: '',
            errors: {
                name: '',
                email: '',
                password: '',
                confirmPassword: ''
            }
        });
    }

    togglePasswordVisibility() {
        this.setState(prevState => ({
            showPassword: !prevState.showPassword
        }));
    }

    getPasswordStrength(password) {
        if (!password) return { strength: 0, label: 'None', color: '#6c757d' };
        
        let score = 0;
        if (password.length >= 8) score++;
        if (/[A-Z]/.test(password)) score++;
        if (/[a-z]/.test(password)) score++;
        if (/[0-9]/.test(password)) score++;
        if (/[!@#$%^&*(),.?":{}|<>]/.test(password)) score++;
        
        const strengths = [
            { strength: 0, label: 'None', color: '#6c757d' },
            { strength: 1, label: 'Weak', color: '#dc3545' },
            { strength: 2, label: 'Fair', color: '#ffc107' },
            { strength: 3, label: 'Good', color: '#17a2b8' },
            { strength: 4, label: 'Strong', color: '#28a745' },
            { strength: 5, label: 'Very Strong', color: '#28a745' }
        ];
        
        return strengths[score] || strengths[0];
    }

    render() {
        const { errors, name, email, password, confirmPassword, showPassword } = this.state;
        const passwordStrength = this.getPasswordStrength(password);

        return (
            <div style={styles.container}>
                <div style={styles.card}>
                    <h1 style={styles.header}>📧 Mail Register</h1>
                    <p style={styles.subHeader}>Create your account</p>
                    
                    <form onSubmit={this.handleSubmit} style={styles.form} noValidate>
                        <div style={styles.formGroup}>
                            <label style={styles.label}>
                                Full Name <span style={styles.required}>*</span>
                            </label>
                            <input
                                type="text"
                                name="name"
                                value={name}
                                onChange={this.handleChange}
                                placeholder="Enter your full name (min 5 characters)"
                                style={{
                                    ...styles.input,
                                    borderColor: errors.name ? '#dc3545' : 
                                                name && !errors.name ? '#28a745' : '#ced4da'
                                }}
                                className={errors.name ? 'error-input' : ''}
                            />
                            {errors.name && (
                                <span style={styles.errorMessage}>{errors.name}</span>
                            )}
                            {name && !errors.name && (
                                <span style={styles.successMessage}>✓ Valid name</span>
                            )}
                        </div>

                        <div style={styles.formGroup}>
                            <label style={styles.label}>
                                Email Address <span style={styles.required}>*</span>
                            </label>
                            <input
                                type="email"
                                name="email"
                                value={email}
                                onChange={this.handleChange}
                                placeholder="Enter your email (must contain @ and .)"
                                style={{
                                    ...styles.input,
                                    borderColor: errors.email ? '#dc3545' : 
                                                email && !errors.email ? '#28a745' : '#ced4da'
                                }}
                                className={errors.email ? 'error-input' : ''}
                            />
                            {errors.email && (
                                <span style={styles.errorMessage}>{errors.email}</span>
                            )}
                            {email && !errors.email && (
                                <span style={styles.successMessage}>✓ Valid email</span>
                            )}
                        </div>

                        <div style={styles.formGroup}>
                            <label style={styles.label}>
                                Password <span style={styles.required}>*</span>
                            </label>
                            <div style={styles.passwordContainer}>
                                <input
                                    type={showPassword ? 'text' : 'password'}
                                    name="password"
                                    value={password}
                                    onChange={this.handleChange}
                                    placeholder="Enter password (min 8 characters)"
                                    style={{
                                        ...styles.input,
                                        ...styles.passwordInput,
                                        borderColor: errors.password ? '#dc3545' : 
                                                    password && !errors.password ? '#28a745' : '#ced4da'
                                    }}
                                    className={errors.password ? 'error-input' : ''}
                                />
                                <button
                                    type="button"
                                    onClick={this.togglePasswordVisibility}
                                    style={styles.eyeButton}
                                >
                                    {showPassword ? '👁️' : '👁️‍🗨️'}
                                </button>
                            </div>
                            {errors.password && (
                                <span style={styles.errorMessage}>{errors.password}</span>
                            )}
                            {password && !errors.password && (
                                <span style={styles.successMessage}>✓ Valid password</span>
                            )}
                            
                            {password && (
                                <div style={styles.strengthContainer}>
                                    <div style={styles.strengthBar}>
                                        <div style={{
                                            ...styles.strengthFill,
                                            width: `${(passwordStrength.strength / 5) * 100}%`,
                                            backgroundColor: passwordStrength.color
                                        }} />
                                    </div>
                                    <span style={{
                                        ...styles.strengthLabel,
                                        color: passwordStrength.color
                                    }}>
                                        Strength: {passwordStrength.label}
                                    </span>
                                </div>
                            )}
                            
                            <div style={styles.requirements}>
                                <p style={{
                                    color: password.length >= 8 ? '#28a745' : '#6c757d'
                                }}>
                                    {password.length >= 8 ? '✅' : '⬜'} At least 8 characters
                                </p>
                                <p style={{
                                    color: /[A-Z]/.test(password) ? '#28a745' : '#6c757d'
                                }}>
                                    {/[A-Z]/.test(password) ? '✅' : '⬜'} At least one uppercase letter
                                </p>
                                <p style={{
                                    color: /[a-z]/.test(password) ? '#28a745' : '#6c757d'
                                }}>
                                    {/[a-z]/.test(password) ? '✅' : '⬜'} At least one lowercase letter
                                </p>
                                <p style={{
                                    color: /[0-9]/.test(password) ? '#28a745' : '#6c757d'
                                }}>
                                    {/[0-9]/.test(password) ? '✅' : '⬜'} At least one number
                                </p>
                                <p style={{
                                    color: /[!@#$%^&*(),.?":{}|<>]/.test(password) ? '#28a745' : '#6c757d'
                                }}>
                                    {/[!@#$%^&*(),.?":{}|<>]/.test(password) ? '✅' : '⬜'} At least one special character
                                </p>
                            </div>
                        </div>

                        <div style={styles.formGroup}>
                            <label style={styles.label}>
                                Confirm Password <span style={styles.required}>*</span>
                            </label>
                            <input
                                type={showPassword ? 'text' : 'password'}
                                name="confirmPassword"
                                value={confirmPassword}
                                onChange={this.handleChange}
                                placeholder="Re-enter your password"
                                style={{
                                    ...styles.input,
                                    borderColor: errors.confirmPassword ? '#dc3545' : 
                                                confirmPassword && !errors.confirmPassword ? '#28a745' : '#ced4da'
                                }}
                                className={errors.confirmPassword ? 'error-input' : ''}
                            />
                            {errors.confirmPassword && (
                                <span style={styles.errorMessage}>{errors.confirmPassword}</span>
                            )}
                            {confirmPassword && !errors.confirmPassword && (
                                <span style={styles.successMessage}>✓ Passwords match</span>
                            )}
                        </div>

                        <button 
                            type="submit" 
                            style={styles.submitButton}
                            onMouseEnter={(e) => e.target.style.transform = 'scale(1.02)'}
                            onMouseLeave={(e) => e.target.style.transform = 'scale(1)'}
                        >
                            📝 Register
                        </button>

                        <button 
                            type="button" 
                            onClick={() => this.resetForm()}
                            style={styles.resetButton}
                        >
                            🔄 Reset
                        </button>
                    </form>

                    <div style={styles.summary}>
                        <p style={styles.summaryTitle}>Form Validation Status:</p>
                        <ul style={styles.summaryList}>
                            <li style={{
                                color: name && !errors.name ? '#28a745' : '#dc3545'
                            }}>
                                {name && !errors.name ? '✅' : '❌'} Name: {name && !errors.name ? 'Valid' : errors.name || 'Required'}
                            </li>
                            <li style={{
                                color: email && !errors.email ? '#28a745' : '#dc3545'
                            }}>
                                {email && !errors.email ? '✅' : '❌'} Email: {email && !errors.email ? 'Valid' : errors.email || 'Required'}
                            </li>
                            <li style={{
                                color: password && !errors.password ? '#28a745' : '#dc3545'
                            }}>
                                {password && !errors.password ? '✅' : '❌'} Password: {password && !errors.password ? 'Valid' : errors.password || 'Required'}
                            </li>
                            <li style={{
                                color: confirmPassword && !errors.confirmPassword ? '#28a745' : '#dc3545'
                            }}>
                                {confirmPassword && !errors.confirmPassword ? '✅' : '❌'} Confirm Password: {confirmPassword && !errors.confirmPassword ? 'Matches' : errors.confirmPassword || 'Required'}
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        );
    }
}

const styles = {
    container: {
        minHeight: '100vh',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        padding: '20px',
        background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)'
    },
    card: {
        backgroundColor: 'white',
        padding: '40px',
        borderRadius: '20px',
        boxShadow: '0 10px 40px rgba(0,0,0,0.2)',
        maxWidth: '550px',
        width: '100%'
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
        gap: '5px'
    },
    label: {
        fontSize: '14px',
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
        transition: 'all 0.3s',
        outline: 'none',
        width: '100%',
        boxSizing: 'border-box'
    },
    passwordContainer: {
        position: 'relative',
        width: '100%'
    },
    passwordInput: {
        paddingRight: '45px'
    },
    eyeButton: {
        position: 'absolute',
        right: '12px',
        top: '50%',
        transform: 'translateY(-50%)',
        background: 'none',
        border: 'none',
        cursor: 'pointer',
        fontSize: '20px',
        padding: '5px'
    },
    errorMessage: {
        color: '#dc3545',
        fontSize: '13px',
        marginTop: '3px'
    },
    successMessage: {
        color: '#28a745',
        fontSize: '13px',
        marginTop: '3px'
    },
    strengthContainer: {
        marginTop: '8px'
    },
    strengthBar: {
        width: '100%',
        height: '6px',
        backgroundColor: '#e9ecef',
        borderRadius: '3px',
        overflow: 'hidden'
    },
    strengthFill: {
        height: '100%',
        transition: 'width 0.3s ease'
    },
    strengthLabel: {
        fontSize: '12px',
        marginTop: '3px',
        display: 'block'
    },
    requirements: {
        marginTop: '8px',
        fontSize: '12px',
        display: 'grid',
        gridTemplateColumns: '1fr 1fr',
        gap: '3px 15px'
    },
    submitButton: {
        backgroundColor: '#667eea',
        color: 'white',
        border: 'none',
        padding: '14px',
        fontSize: '18px',
        fontWeight: '600',
        borderRadius: '8px',
        cursor: 'pointer',
        transition: 'all 0.3s',
        marginTop: '10px'
    },
    resetButton: {
        backgroundColor: '#6c757d',
        color: 'white',
        border: 'none',
        padding: '12px',
        fontSize: '16px',
        fontWeight: '500',
        borderRadius: '8px',
        cursor: 'pointer',
        transition: 'all 0.3s'
    },
    summary: {
        marginTop: '25px',
        padding: '15px',
        backgroundColor: '#f8f9fa',
        borderRadius: '10px',
        border: '1px solid #dee2e6'
    },
    summaryTitle: {
        margin: '0 0 10px 0',
        fontSize: '14px',
        fontWeight: '600',
        color: '#495057'
    },
    summaryList: {
        listStyle: 'none',
        padding: '0',
        margin: '0',
        fontSize: '13px'
    }
};

export default Register;
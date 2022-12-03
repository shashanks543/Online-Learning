const form = document.getElementById('form');
const username = document.getElementById('username');
const email = document.getElementById('email');
const mobile = document.getElementById('mobile');
const password = document.getElementById('password');
const password2 = document.getElementById('password2');

form.addEventListener('submit', e =>{
    e.preventDefault();

    validateInputs();
})

const setError = (element, message) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = message;
    inputControl.classList.add('error');
    inputControl.classList.remove('success')
}

const setSuccess = element => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('error');

    errorDisplay.innerText = '';
    inputControl.classList.add('success');
    inputControl.classList.remove('error');
}

const isValidEmail = email => {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}



const validateInputs = () => {
    const usernameValue = username.value.trim();
    const emailValue = email.value.trim();
    const passwordValue = password.value.trim();
    const mobileValue = mobile.value.trim();
    const password2Value = password2.value.trim();

    if(usernameValue === '')
    {
        setError(username, 'Username is required');
    }
    else
    {
        setSuccess('username');
    }


    if(emailValue === '')
    {
        setError(email, 'Email is required');
    }
    else if(!isValidEmail(emailValue))
    {
        setError(email, 'Provide a valid email');
    }
    else
    {
        setSuccess('email');
    }

    if(mobileValue === '')
    {
        setError(mobile, 'Mobile no required');
    }
    else if(mobileValue.length == 10)
    {
        setError(mobile, 'Mobile no must be at least 10 numbers');
    }
    else
    {
        setSuccess(mobile);
    }
    
    if(password === '')
    {
        setError(password, 'Password is required');
    }
    else if(passwordValue.length < 8)
    {
        setError(password, 'Password must be at least 8 character');
    }
    else
    {
        setSuccess(password);
    }

    
    if(password2 === '')
    {
        setError(password2, 'Password is required');
    }
    else if(password2Value !== passwordValue)
    {
        setError(password2, "Password doesn't match");
    }
    else
    {
        setSuccess(password2);
    }
};
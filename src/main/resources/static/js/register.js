window.onload = function () {

    const elements = document.querySelectorAll('[type="password"]');
    elements.forEach(function (elem) {

        elem.parentNode.querySelector('i').addEventListener('click', function () {
            if (elem.type === "password") {
                elem.type = "text";
                this.className = 'fa fa-eye-slash showpwd';
            } else {
                elem.type = "password";
                this.className = 'fa fa-eye showpwd';
            }
        });
    });

};

function onSubmit(token) {
    
   if (document.getElementById("register-form").reportValidity()) {
        document.getElementById("register-form").submit();
    }
   
}
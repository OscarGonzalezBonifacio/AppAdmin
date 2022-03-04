// Boton Flotante con el footer
const btnFlotante = document.querySelector('.btn-flotante');

btnFlotante.addEventListener('click', function() {


    const footer = document.querySelector('.footer');


    if( footer.classList.contains('activo') ) {
        footer.classList.remove('activo');
        this.classList.remove('activo');
        this.innerText = 'información';
    } else {
        footer.classList.add('activo');
        this.classList.add('activo');
        this.innerText = 'X Cerrar';
    }

});
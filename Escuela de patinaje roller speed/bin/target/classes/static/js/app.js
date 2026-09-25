const menuToggle = document.querySelector('.menu-toggle');
const navLinks = document.querySelector('.nav-links');
const links = document.querySelectorAll('.nav-link');
const sections = document.querySelectorAll('.section-anchor');
const form = document.querySelector('#enrollment-form');
const status = document.querySelector('#form-status');

menuToggle.addEventListener('click', () => {
    const isOpen = navLinks.classList.toggle('open');
    menuToggle.setAttribute('aria-expanded', String(isOpen));
});

links.forEach((link) => link.addEventListener('click', () => {
    navLinks.classList.remove('open');
    menuToggle.setAttribute('aria-expanded', 'false');
}));

const observer = new IntersectionObserver((entries) => {
    entries.forEach((entry) => {
        if (!entry.isIntersecting) return;
        links.forEach((link) => link.classList.toggle('active', link.getAttribute('href') === `#${entry.target.id}`));
    });
}, { rootMargin: '-35% 0px -55% 0px' });
sections.forEach((section) => observer.observe(section));

form.addEventListener('submit', async (event) => {
    event.preventDefault();
    status.textContent = 'Enviando solicitud...';
    const data = Object.fromEntries(new FormData(form));
    try {
        const response = await fetch('/alumnos', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });
        if (!response.ok) throw new Error('No se pudo registrar');
        form.reset();
        status.textContent = 'Solicitud recibida. Nos pondremos en contacto contigo.';
    } catch (error) {
        status.textContent = 'No fue posible enviar la solicitud. Intenta nuevamente.';
    }
});

document.addEventListener('DOMContentLoaded', () => {
    const scroller = document.querySelector('.game-scroller');
    let speed = 0;
  
    // Continuous horizontal scrolling animation
    function animateScroll() {
      scroller.scrollLeft += speed;
      requestAnimationFrame(animateScroll);
    }
    animateScroll();
  
    // Adjust scroll speed based on mouse horizontal position
    scroller.addEventListener('mousemove', (e) => {
      const rect = scroller.getBoundingClientRect();
      const x = e.clientX - rect.left;  // Mouse X relative to scroller
      const midpoint = rect.width / 2;
      // The further from the center, the faster the scroll (adjust multiplier as needed)
      speed = (x - midpoint) * 0.03;
    });
  
    // Stop scrolling when mouse leaves the scroller area
    scroller.addEventListener('mouseleave', () => {
      speed = 0;
    });
  
    // Toggle review form on clicking a game image
    const images = document.querySelectorAll('.game-image');
    images.forEach(img => {
      img.addEventListener('click', () => {
        const card = img.closest('.game-card');
        const formContainer = card.querySelector('.review-form-container');
        // Toggle display between block and none
        formContainer.style.display = (formContainer.style.display === 'block') ? 'none' : 'block';
      });
    });
  
    // Filter and search functionality
    const filterSelect = document.getElementById('filter');
    const searchInput = document.getElementById('search');
    const gameCards = document.querySelectorAll('.game-card');
  
    function filterGames() {
      const selectedGenre = filterSelect.value.toLowerCase();
      const searchTerm = searchInput.value.toLowerCase();
  
      gameCards.forEach(card => {
        const genre = card.getAttribute('data-genre').toLowerCase();
        const title = card.getAttribute('data-title').toLowerCase();
        const genreMatch = selectedGenre === 'all' || genre === selectedGenre;
        const titleMatch = title.includes(searchTerm);
        card.style.display = (genreMatch && titleMatch) ? 'block' : 'none';
      });
    }
  
    filterSelect.addEventListener('change', filterGames);
    searchInput.addEventListener('input', filterGames);
  });

  
$(document).ready(function() {
    $('#category-filter').change(function() {
        let selectedCategory = $(this).val();

        $('.suggestion-item').each(function() {
            let itemCategories = $(this).data('category').split(' ');

            if (selectedCategory === 'all' || itemCategories.includes(selectedCategory)) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    });

    $('.suggestion').click(function() {
        let imageUrl = $(this).find('img').attr('src');
        let name = $(this).data('name');
        let description = $(this).data('description');
        let price = $(this).data('price');
        let platforms = $(this).data('platforms');

        $('#popup-image').attr('src', imageUrl);
        $('#popup-name').text(name);
        $('#popup-description').text(description);
        $('#popup-price').text("Price: " + price);
        $('#popup-platforms').text("Platforms: " + platforms);
        $('#popup').show();
    });

    $('#close-popup').click(function() {
        $('#popup').hide();
    });
});
$(document).ready(function () {
    $('#contact_form').bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            saler: {
                validators: {
                    notEmpty: {
                        message: 'Необходимо выбрать продавца'
                    }
                }
            },
            product: {
                validators: {
                    notEmpty: {
                        message: 'Необходимо выбрать продукт'
                    }
                }
            },
            client: {
                validators: {

                    notEmpty: {
                        message: 'Необходимо выбрать клиента'
                    }
                }
            }
        }
    })
        .on('success.form.bv', function (e) {
            $('#success_message').slideDown({opacity: "show"}, "slow") // Do something ...
            $('#contact_form').data('bootstrapValidator').resetForm();

            // Prevent form submission
            e.preventDefault();

            // Get the form instance
            var $form = $(e.target);

            // Get the BootstrapValidator instance
            var bv = $form.data('bootstrapValidator');

            // Use Ajax to submit form data
            $.post($form.attr('action'), $form.serialize(), function (result) {
                console.log(result);
            }, 'json');
        });

    var clientSelector = $("#select-client");

    var clientId = clientSelector.val();
    if (clientId != '') {
        getPurchases(clientId)
    }

    clientSelector.change(function () {
        var clientId = clientSelector.val();
        if (clientId != '') {
            getPurchases(clientId)
        }
    });

});

function getPurchases(clientId) {
    $.ajax({
        url: "/sale/purchases",
        type: "get",
        data: {
            "clientId": clientId
        },
        success: function (data) {
            $('#purchases-table').replaceWith(data);
        },
        error: function () {
            console.log("There was an error");
        }

    });
}
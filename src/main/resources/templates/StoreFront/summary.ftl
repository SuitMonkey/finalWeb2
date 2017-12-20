<#include "layouts/header.ftl">
<body>
<#include "layouts/navigation.ftl">


<!-- catg header banner section -->
<section id="aa-catg-head-banner">
    <img src="img/fashion/fashion-header-bg-8.jpg" alt="fashion img">
    <div class="aa-catg-head-banner-area">
        <div class="container">
            <div class="aa-catg-head-banner-content">
                <h2>Checkout Page</h2>
                <ol class="breadcrumb">
                    <li><a href="/">Home</a></li>
                    <li class="active">Summary</li>
                </ol>
            </div>
        </div>
    </div>
</section>
<!-- / catg header banner section -->


<!-- Cart view section -->
<section id="checkout">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="checkout-area">
                    <form action="/confirm_transaction" id="form2" method="post" enctype="multipart/form-data">
                        <div class="row">
                            <div class="col-md-3">
                                <input type="hidden" name="fiscalCode" value="${fiscalCode}">
                            </div>
                            <div class="col-md-6">
                                <div class="checkout-right">
                                <#if shoppingCart?has_content>
                                    <h4>Order Summary</h4>
                                    <div class="aa-order-summary-area">
                                        <table class="table table-responsive">
                                            <thead>
                                            <tr>
                                                <th>Product</th>
                                                <th>Total</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <#list shoppingCart as item>
                                                <tr>
                                                    <td>${item.getProductName()} <strong> x  1</strong></td>
                                                    <td>$${item.getProductPrice()}</td>
                                                </tr>
                                                </#list>
                                            </tbody>
                                            <tfoot>
                                            <tr>
                                                <th>Total</th>
                                                <td>$${total}</td>
                                            </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                    <h4>Payment Method</h4>
                                    <div class="aa-payment-method">
                                        <label for="cashdelivery"><input type="radio" id="cashdelivery" name="optionsRadios"> Cash on Delivery </label>
                                        <label for="paypal"><input type="radio" id="paypal" name="optionsRadios" checked> Via Paypal </label>
                                        <br>
                                        <img src="https://www.paypalobjects.com/webstatic/mktg/logo/AM_mc_vs_dc_ae.jpg" border="0" alt="PayPal Acceptance Mark">
                                        <input type="button" value="Get Receip" class="aa-browse-btn" onclick="submitForm2()">
                                    </div>
                                </#if>
                                </div>
                            </div>
                            <div class="col-md-3">

                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<script>
    submitForm2 = function(){
        document.getElementById("form2").submit();
    }
</script>
<!-- / Cart view section -->
<#include "layouts/footer.ftl">

<#include "/Backend/layouts/header.ftl">

<body class="nav-md">
<#include  "/Backend/layouts/_loader.ftl">
<div class="container body">
    <div class="main_container">
    <#include "/Backend/layouts/sidebar.ftl">
    <#include "/Backend/layouts/navbar.ftl">



        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3><spring:message code="uParam" arguments="TEST 1" htmlEscape="false" /></h3>
                    </div>
                </div>
                <div class="clearfix"></div>
                <!--ADD CONTENT HERE-->
                <div class="row col-md-12">
                    <div class="col-md-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2><@spring.message "uform" /></h2>
                                <div class="clearfix"></div>
                            </div>

                <div class="x_content">
                    <br />
                    <form action="/edit_user" METHOD="POST" enctype="multipart/form-data" id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">

                        <div class="form-group item">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email"><@spring.message "email" /> <span class="required">*</span>
                            </label><br>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <#--<#if ()??></#if>-->
                                <input type="text" id="email" name="email" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>

                        <div class="form-group item">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first"><@spring.message "first" /> <span class="required">*</span>
                            </label><br>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <#--<#if ()??></#if>-->
                                <input type="text" id="first" name="first" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>

                        <div class="form-group item">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last"><@spring.message "last" /> <span class="required">*</span>
                            </label><br>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <#--<#if ()??></#if>-->
                                <input type="text" id="last" name="last" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="country"><@spring.message "country" /> <span
                                    class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select id="country" name="country" required="required" class="form-control col-md-7 col-xs-12"></select>

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="state"><@spring.message "city" /> <span
                                    class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select id="state" name="state" required="required" class="form-control col-md-7 col-xs-12"></select>
                            </div>
                        </div>

                        <div class="form-group item">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="address"><@spring.message "address" /> <span class="required">*</span>
                            </label><br>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                            <#--<#if ()??></#if>-->
                                <input type="text" id="address" name="address" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>

                        <div class="form-group item">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="password"><@spring.message "password" /> <span class="required">*</span>
                            </label><br>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="password" id="password" name="password" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>

                        <div class="form-group item">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="password2"><@spring.message "confirm" /> <@spring.message "password" /> <span class="required">*</span>
                            </label><br>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="password" id="password2" name="password2" data-validate-linked="password" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>

                        <div class="form-group item">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12"><@spring.message "ValorFiscal" /> <span class="required">*</span></label>
                            <div class="aa-checkout-single-bill">
                            <#--<#if ()??></#if>-->
                                <select name="valorFiscal">
                                    <option value="" disabled selected hidden>Please Choose...</option>
                                    <option value="false">Persona Fisica</option>
                                    <option value="true">Persona Judidica</option>
                                </select>
                            </div>
                        </div>


                        <div class="form-group item">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="RNC"><@spring.message "RNC" /> <span class="required">*</span>
                            </label><br>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                            <#--<#if ()??></#if>-->
                                <input type="text" id="RNC" name="RNC" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>

                        <div class="form-group item">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12"><@spring.message "role" /> <span class="required">*</span></label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                            <#--<#if ()??></#if>-->
                                <div class="radio">
                                    <label><input type="radio" name="role" id="role1" value="ADMIN" required checked>ADMIN</label>
                                </div>
                                <div class="radio">
                                    <label><input type="radio" name="role" id="role2"  value="USER">USER</label>
                                </div>
                                <div class="radio">
                                    <label><input type="radio" name="role" id="role3"  value="STORAGE">STORAGE</label>
                                </div>
                            </div>
                        </div>


                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                <button id="send" type="submit" class="btn btn-success"><@spring.message "submit" /></button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- /page content -->
    <#include "/Backend/layouts/Copyright.ftl">


    </div>
</div>
<#include "/Backend/layouts/footer.ftl">
<#include "/Backend/users/_usersScripts.ftl">
<!-- /Datatables -->
<#include "/Backend/layouts/pageCloser.ftl">



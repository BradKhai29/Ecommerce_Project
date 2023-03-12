<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ADMIN LOGIN PAGE</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${root}/asset/home/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"/>
    </head>
    <body>
        <header class="container-fluid">
            <div class="row">
                <!-- Navbar -->
                <nav class="navbar navbar-expand-lg navbar-scroll shadow-0 border-bottom border-dark">
                    <div class="container align-items-center justify-content-center pt-1">
                        <div class="badge">
                            <a href="${root}" id="icon" class="btn fw-bold fs-3"><i class="fa-solid fa-cubes"></i>BLOCKY</a>
                        </div>
                    </div>
                </nav>
            </div>
        </header>
        <main class="container-sm mt-5 mb-5">
            <div class="row">
                <section class="container-sm">
                    <div class="row">
                        <div class="col"></div>
                        <div class="col-8">
                            <div class="divider d-flex align-items-center mb-4">
                                <h4 class="text-center fw-bold mx-3 mb-0"><span class="badge bg-primary bg-opacity-75">ADMIN LOGIN</span></h4>
                            </div>
                            <form action="admin" method="post" class="border border-2 border-dark rounded-3 p-4 bg-dark text-light">
                                <div class="mb-3">
                                    <input type="text" name="username" class="form-control" id="userName" placeholder="Tên đăng nhập" required ></td>
                                </div>
                                <div class="mb-3">
                                    <input type="password" name="password" class="form-control" id="password" placeholder="Mật khẩu" required/></td>
                                </div>
                                <div class="d-grid gap-2 mt-5 fw-bold">
                                    <button type="submit" class="btn btn-danger" name="command" value="LOGIN">Đăng nhập</button>
                                </div>
                            </form>
                        </div>
                        <div class="col"></div>
                    </div>
                </section>
            </div>
        </main>
        <div class="container p-3">
            <div class="row text-center" style="color: white!important;">.</div>
        </div>
        <footer class="text-center text-lg-start text-light bg-dark mt-5">
            <section class="">
                <div class="container text-center text-md-start mt-5">
                    <!-- Grid row -->
                    <div class="row mt-3 pt-3">
                        <!-- Grid column -->
                        <div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">
                            <!-- Content -->
                            <h6 class="text-uppercase fw-bold">Company name</h6>
                            <hr class="mb-4 mt-0 d-inline-block mx-auto myslash"/>
                            <p>
                                Here you can use rows and columns to organize your footer
                                content. Lorem ipsum dolor sit amet, consectetur adipisicing
                                elit.
                            </p>
                        </div>
                        <!-- Grid column -->

                        <!-- Grid column -->
                        <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">
                            <!-- Links -->
                            <h6 class="text-uppercase fw-bold">Products</h6>
                            <hr class="mb-4 mt-0 d-inline-block mx-auto myslash"/>
                            <p>
                                <a href="#!" class="text-dark">MDBootstrap</a>
                            </p>
                            <p>
                                <a href="#!" class="text-dark">MDWordPress</a>
                            </p>
                            <p>
                                <a href="#!" class="text-dark">BrandFlow</a>
                            </p>
                            <p>
                                <a href="#!" class="text-dark">Bootstrap Angular</a>
                            </p>
                        </div>
                        <!-- Grid column -->

                        <!-- Grid column -->
                        <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">
                            <!-- Links -->
                            <h6 class="text-uppercase fw-bold">Useful links</h6>
                            <hr class="mb-4 mt-0 d-inline-block mx-auto myslash"/>
                            <p>
                                <a href="#!" class="text-dark">Your Account</a>
                            </p>
                            <p>
                                <a href="#!" class="text-dark">Become an Affiliate</a>
                            </p>
                            <p>
                                <a href="#!" class="text-dark">Shipping Rates</a>
                            </p>
                            <p>
                                <a href="#!" class="text-dark">Help</a>
                            </p>
                        </div>
                        <!-- Grid column -->

                        <!-- Grid column -->
                        <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">
                            <!-- Links -->
                            <h6 class="text-uppercase fw-bold">Contact</h6>
                            <hr class="mb-4 mt-0 d-inline-block mx-auto myslash"/>

                            <p><i class="fas fa-home mr-3"></i> New York, NY 10012, US</p>
                            <p><i class="fas fa-envelope mr-3"></i> info@example.com</p>
                            <p><i class="fas fa-phone mr-3"></i></p>
                            <p><i class="fas fa-print mr-3"></i> + 01 234 567 89</p>
                        </div>
                        <!-- Grid column -->
                    </div>
                    <!-- Grid row -->
                </div>
            </section>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>

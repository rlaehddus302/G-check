import { Outlet, useNavigate } from "react-router-dom"
import classes from './NavBar.module.css'

export default function NavBar()
{
    const navigate = useNavigate()
    function navigationHandler(link)
    {
        navigate("/" + link)
    }
    return(
        <>
            <section className='d-flex flex-column vw-100 vh-100 bg-body-tertiary'>
                <nav style={{height:"4.0em"}} className="navbar navbar-expand-lg bg-white border-bottom border-dark-subtle py-0">
                    <div className="container-fluid h-100 px-2 px-lg-5 px-md-4 px-sm-3">
                        <div className="d-flex align-items-center gap-3">
                            <div className={`d-flex align-items-center justify-content-center bg-gradient-custom rounded-3 ${classes.gradient} `} style={{width: "2.0rem", height: "2.0rem"}}>
                                <span className="text-white fw-bold fs-5">G</span>
                            </div>
                            <span style={{fontSize: "1.3em"}} className="m-0 fw-bold">G-Check</span>
                        </div>
                        <div className="d-flex align-items-center gap-3">
                            <button onClick={() => navigationHandler("login")} type="button" style={{fontSize: "0.8em", fontWeight: "bold"}} class="btn btn-light p-2">로그인</button>
                            <button onClick={() => navigationHandler("signUp")} type="button" style={{"--bs-btn-bg": "#AD46FF","--bs-btn-border-color": "#AD46FF","--bs-btn-hover-bg": "#922fe0","--bs-btn-hover-border-color": "#922fe0", "--bs-btn-active-bg" : "#922fe0", "--bs-btn-active-border-color" : "#922fe0" ,fontSize: "0.8em", fontWeight: "bold", color: "white"}} class="btn p-2">회원가입</button>
                        </div>
                    </div>
                </nav>
                <Outlet/>
            </section>
        </>
    )
}
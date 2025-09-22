import { Outlet, useLoaderData, useNavigate, useRevalidator } from "react-router-dom"
import classes from './NavBar.module.css'
import Cookies from 'js-cookie';

export default function NavBar()
{
    const navigate = useNavigate()
    let isAuthentication = useLoaderData();
    const { revalidate, state } = useRevalidator();
    let name
    if(isAuthentication)
    {
        name = localStorage.getItem("name")
    }
    function navigationHandler(link)
    {
        navigate("/" + link)
    }
    async function logout() 
    {
        const csrf = Cookies.get("XSRF-TOKEN");
        console.log(csrf)
        const response = await fetch('http://localhost:8080/logout', {
            method: 'POST',
            headers: {
            'Content-Type': 'application/json',
            'X-XSRF-TOKEN' : csrf,
            },
            credentials: 'include',
        });
        if(response.ok)
        {
            localStorage.clear();
            navigate("/")
            revalidate()
        }    
    }
    return(
        <>
            <section className='d-flex flex-column min-vh-100 bg-body-tertiary'>
                <nav className="navbar navbar-expand-lg bg-white border-bottom border-dark-subtle py-3">
                    <div className="container-fluid h-100 px-2 px-lg-5 px-md-4 px-sm-3">
                        <div className="d-flex align-items-center gap-3">
                            <div className={`d-flex align-items-center justify-content-center bg-gradient-custom rounded-3 ${classes.gradient} `} style={{width: "2.0rem", height: "2.0rem"}}>
                                <span className="text-white fw-bold fs-5">G</span>
                            </div>
                            <span style={{fontSize: "1.3em"}} className="m-0 fw-bold">G-Check</span>
                        </div>
                        <div className="d-flex align-items-center gap-3">
                            {
                                isAuthentication ?       
                                <>  <div className="d-flex justify-content-center align-items-center">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-person-circle" viewBox="0 0 16 16">
                                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                                            <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8m8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1"/>
                                        </svg>
                                        <span className="ms-3">{name}님</span>
                                    </div>
                                    <button onClick={logout} type="button" style={{fontSize: "0.8em", fontWeight: "bold"}} className="d-flex align-items-center btn btn-light p-2">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-box-arrow-right" viewBox="0 0 16 16">
                                            <path fill-rule="evenodd" d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0z"/>
                                            <path fill-rule="evenodd" d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708z"/>
                                        </svg>
                                        <p className="m-0 ms-3">
                                            로그아웃
                                        </p>
                                    </button>
                                </>                      
                                :
                                <>
                                    <button onClick={() => navigationHandler("login")} type="button" style={{fontSize: "0.8em", fontWeight: "bold"}} className="btn btn-light p-2">로그인</button>
                                    <button onClick={() => navigationHandler("signUp")} type="button" style={{"--bs-btn-bg": "#AD46FF","--bs-btn-border-color": "#AD46FF","--bs-btn-hover-bg": "#922fe0","--bs-btn-hover-border-color": "#922fe0", "--bs-btn-active-bg" : "#922fe0", "--bs-btn-active-border-color" : "#922fe0" ,fontSize: "0.8em", fontWeight: "bold", color: "white"}} className="btn p-2">회원가입</button>
                                </>
                            }
                        </div>
                    </div>
                </nav>
                <Outlet/>
            </section>
        </>
    )
}
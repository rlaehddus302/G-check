import { useState } from 'react'
import classes from './DropDown.module.css'

export default function DropDown({defaultSelect, list, handleSelect, width})
{
    const [check, setCheck] = useState("active")
    return(
        <div className={`dropdown-center ${classes.wrapper}`}>
            <button style={{width:width}} onClick={() => setCheck("active")} className="btn dropdown-toggle d-inline-flex align-items-center justify-content-between" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                {defaultSelect}
            </button>
            <ul className="dropdown-menu">
                {
                    list.map((value, index)=>{
                        if(value === defaultSelect)
                        {
                            return(
                                <li onClick={() => {handleSelect(value)}} key={index}>
                                    <div style={{width:width}} className={`d-flex align-items-center dropdown-item ${check}`}>
                                        <div className='me-auto'>{value}</div>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" className="bi bi-check" viewBox="0 0 16 16">
                                            <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425z"/>
                                        </svg>
                                    </div>
                                </li>
                            )
                        }
                        else
                        {
                            return(
                                <li key={index} onClick={() => {handleSelect(value)}}>
                                    <div style={{width:width}} onMouseOver={() => setCheck("")} className="dropdown-item">
                                        {value}
                                    </div>
                                </li>
                            )
                        }
                    })
                }
            </ul>
        </div>
    )
}
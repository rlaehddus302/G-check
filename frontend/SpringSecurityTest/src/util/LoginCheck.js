export async function loginCheck()
{
    try
    {
        const response = await fetch("http://localhost:8080/test", {
            credentials: 'include'
        });    
        if(response.ok)
        {
            return true;
        }
        else
        {
            localStorage.clear();
            return false;
        }
    }
    catch
    {
        return false;
    }
}
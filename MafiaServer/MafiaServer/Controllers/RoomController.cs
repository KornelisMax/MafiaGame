using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MafiaServer.Models;
using MafiaServer.Repository;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;

namespace MafiaServer.Controllers
{
    
    [ApiController]
    public class RoomController : ControllerBase
    {
        private readonly MafiaContext _context;

        public RoomController(MafiaContext context)
        {
            _context = context;
        }



        // GET: api/Room
        [HttpGet]
        [Route("api/GetP")]
        public IEnumerable<string> Get()
        {
            return new string[] { "value1", "value2" };
        }

        // GET: api/Room/5
        //[HttpGet]
        //[Route("api/GetRooms")]
        //public List<Room> GetRooms()
        //{
        //    return List < Room > n = new List<Room>
        //        ();
        //}

        // POST: api/Room
        [HttpPost]
        [Route("api/CreateNewRoom")]
        public void PostNewRoom(string name, int civilAmount, int mafiaAmount, Guid creatorId)
        {
          
            
            _context.Rooms.Add(new Room() { ID = new Guid() });
            _context.SaveChanges();
           
            

        }

        // PUT: api/Room/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody] string value)
        {
        }

        // DELETE: api/ApiWithActions/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
        }
    }
}

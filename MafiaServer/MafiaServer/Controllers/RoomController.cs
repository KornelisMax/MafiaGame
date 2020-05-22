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
        [Route("api/GetPlayers")]
        public List<Player> Get()
        {
            //Dataset.AddSamplePlayersToDb(_context);
            return _context.Players.ToList();
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
        public void CreateNewRoom(string name, int civilAmount, int mafiaAmount, string playerName)
        {
            _context.Players.Add(new Player() { PlayerId = new Guid(), Name = playerName, RoomId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4961") });
            Room room = new Room()
            {
                RoomId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4961"),
                CivilAmount = civilAmount,
                MafiaAmount = mafiaAmount,
                Name = name,
                Players = _context.Players.ToList()
            };
            _context.Rooms.Add(room);
            _context.SaveChanges();
        }

        // PUT: api/Room/5
        [HttpPut("{id}")]
        public async void Put(int id, [FromBody] string value)
        {
        }

        // DELETE: api/ApiWithActions/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
        }
    }
}

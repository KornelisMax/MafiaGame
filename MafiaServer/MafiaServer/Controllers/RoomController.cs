using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MafiaServer.Models;
using MafiaServer.Repository;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.CodeAnalysis;
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

        [HttpGet]
        [Route("api/GetAlivePlayers")]
        public List<Player> GetAlive()
        {
            //Dataset.AddSamplePlayersToDb(_context);
            return _context.Players.Where(x => x.IsAlive == true).ToList();
        }

        // GET: api/Room
        [HttpGet]
        [Route("api/GetPlayers")]
        public List<Player> Get()
        {
            //Dataset.AddSamplePlayersToDb(_context);
            return _context.Players.ToList();
        }

        [HttpGet]
        [Route("api/FillData")]
        public void FillData()
        {
            Dataset.AddSamplePlayersToDb(_context);
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
        [Consumes("application/x-www-form-urlencoded")]
        public void CreateNewRoom([FromForm]Class classResponder)
        {   
            //Console.WriteLine(classResponder.name);
            _context.Players.Add(new Player() { PlayerId = new Guid(), Name = classResponder.playerName, RoomId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4960") });
            Room room = new Room()
            {
                RoomId = Guid.NewGuid(),
                CivilAmount = classResponder.civilAmount,
                MafiaAmount = classResponder.mafiaAmount,
                Name = classResponder.name,
                Players = _context.Players.ToList()
            };
            _context.Rooms.Add(room);
            _context.SaveChanges();
        }

        [HttpPost]
        [Route("api/Vote")]
        [Consumes("application/x-www-form-urlencoded")]
        public void Vote([FromForm]VoteResponder classResponder)
        {
            //Console.WriteLine(classResponder.name);
            var votingPlayerId = _context.Players.Where(x => x.Name == classResponder.votingPlayer).FirstOrDefault().PlayerId;
            var votedPlayerId = _context.Players.Where(x => x.Name == classResponder.votedPlayer).FirstOrDefault().PlayerId;
            _context.Votes.Add(new Vote() { VotedPlayerId = votedPlayerId, VoteId = Guid.NewGuid(), VotingPlayerId = votingPlayerId });
            //_context.Rooms.Add(room);
            Service service = new Service();
            service.KillPlayer(_context);
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

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

        [HttpGet]
        [Route("api/GetGameStatus")]
        public int GetGameStatus(string votingPlayer)
        {
            Service service = new Service();
            int flag = service.WhichSideWon(_context, votingPlayer);
            //Dataset.AddSamplePlayersToDb(_context);
            return flag;
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
            _context.Players.Add(new Player() { PlayerId = new Guid(), IsAlive = true, Name = "Tomas", RoomId = new Guid("b7afd4f1-9221-482e-966b-5456ae190100"), Role = "Civilian" +
                "" });
            _context.SaveChanges();
            //Dataset.AddSamplePlayersToDb(_context);
        }

        [HttpGet]
        [Route("api/GetPlayersEndGame")]
        public List<Player> GetAllPlayersFromRoom()
        {
            Guid roomId = new Guid("b7afd4f1-9221-482e-966b-5456ae190100");
            //hardcoded roomid
            return _context.Players.Where(x => x.RoomId == roomId).ToList();
        }

        // POST: api/Room
        [HttpPost]
        [Route("api/CreateNewRoom")]
        [Consumes("application/x-www-form-urlencoded")]
        public void CreateNewRoom([FromForm]Class classResponder)
        {
            Service service = new Service();
            //Console.WriteLine(classResponder.name);
            _context.Players.Add(new Player() { PlayerId = new Guid(), IsAlive = true, Name = classResponder.playerName, RoomId = new Guid("b7afd4f1-9221-482e-966b-5456ae190100"), Role = "Civilian" });

            //Room room = new Room()
            //{
            //    RoomId = Guid.NewGuid(),
            //    CivilAmount = classResponder.civilAmount,
            //    MafiaAmount = classResponder.mafiaAmount,
            //    Name = classResponder.name,
            //    Players = _context.Players.ToList()
            //};
            //service.UpdateRoomParameters(_context, classResponder);
            //_context.Rooms.Add(room);
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
            //service.WhichSideWon(_context, classResponder.votingPlayer);
            _context.SaveChanges();
            service.KillPlayer(_context);
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
